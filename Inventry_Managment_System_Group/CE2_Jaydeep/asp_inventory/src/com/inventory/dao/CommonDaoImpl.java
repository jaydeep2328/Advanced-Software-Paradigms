package com.inventory.dao;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inventory.model.Item;
import com.inventory.model.ItemLocation;
import com.inventory.model.QuotationItems;
import com.inventory.model.QuotationMaster;
import com.inventory.model.StockCategory;
import com.inventory.model.UserBuy;
import com.inventory.model.UserRegistration;

@Repository("CommonDao")
public class CommonDaoImpl implements CommonDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	DateFormat dateandtime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public <T> Boolean save(T t) {
		Session session = null;
		Transaction tr = null;
		try {
			session = getSessionFactory().openSession();
			tr = session.beginTransaction();
			session.save(t);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
			return false;
		} finally {
			session.close();
		}

	}
	
	@Override
	public <T> List<T> getListByRistrictionsWithProjections(Class<T> c, String[] columns, Map<String, Object> map, String orderBy, String orderType, Integer firstIndex, Integer maxResult) {
		Session session = null;
		Criteria criteria = null;
		List<T> list = null;
		try {
			session = getSessionFactory().openSession();
			criteria = session.createCriteria(c);
			if (map != null) {
					criteria.add(Restrictions.allEq(map));
			}

			if (firstIndex != null && !firstIndex.equals("") && maxResult != null && !maxResult.equals("")) {
				criteria.setFirstResult(firstIndex);
				criteria.setMaxResults(maxResult);
			}

			if (orderBy != null && !orderBy.equals("")) {
				if ("2".equalsIgnoreCase(orderType) || "desc".equalsIgnoreCase(orderType)) {
					criteria.addOrder(Order.desc(orderBy));
				} else {
					criteria.addOrder(Order.asc(orderBy));
				}
			}
			
			if(columns != null && columns.length > 0){
            	criteria.setProjection(getProjectionList(columns));
            	criteria.setResultTransformer(Transformers.aliasToBean(c));
            }

			list = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public <T> List<T> getListByRistrictions(Class<T> c, Map<String, Object> map, String orderBy, String orderType, Integer firstIndex, Integer maxResult) {
		
		return getListByRistrictionsWithProjections(c, null, map, orderBy, orderType, firstIndex, maxResult);
	}

	@Override
	public <T> List<T> getListByRistrictions(Class<T> c, Map<String, Object> map, String orderBy) {
		return getListByRistrictions(c, map, orderBy, "asc", 0, 0);
	}

	@Override
	public <T> T get(Class<T> c, Serializable id) {
		Session session = null;
		T t = null;
		try {
			session = this.getSessionFactory().openSession();
			t = (T) session.get(c, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return t;
	}
	
	@Override
	public UserRegistration getLoginDetails(String emailid, String password) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		UserRegistration userRegistration = null;
		try{
			session = getSessionFactory().openSession();
			
			Query query = session.createQuery("from UserRegistration where emailid = ? and password = ?");
			query.setParameter(0, emailid);
			query.setParameter(1, password);
			userRegistration = (UserRegistration) query.uniqueResult();
			if(userRegistration != null){
				return userRegistration;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return null;
	}
	
	public ProjectionList getProjectionList(String[] columnNames) {

		ProjectionList projectionList = Projections.projectionList();

		for (String column : columnNames) {
			projectionList.add(Projections.property(column), column);
		}

		return projectionList;
	}

	@Override
	public boolean saveQuotation(QuotationMaster master, List<String> data) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tr = null;
		boolean flag = false;
		try{
			session = getSessionFactory().openSession();
			tr = session.beginTransaction();
			Serializable serializable = session.save(master);
			if(null != serializable){
				flag = true;
				String id = serializable.toString();
				for(String s : data){
					QuotationItems items = new QuotationItems();
					items.setItem_id(Integer.parseInt(s.split("#")[0]));
					items.setQuantity(Integer.parseInt(s.split("#")[1]));
					items.setPpi(Integer.parseInt(s.split("#")[2]));
					items.setTotal(Integer.parseInt(s.split("#")[3]));
					items.setQuotation_id(Integer.parseInt(id));
					Serializable s1 = session.save(items);
					if(s1 != null){
						Item item = (Item) session.createQuery("from Item where item_id = "+Integer.parseInt(s.split("#")[0])).uniqueResult();
						int count = item.getPurchased();
						int newcount = count + Integer.parseInt(s.split("#")[1]);
						Query query = session.createQuery("update Item set purchased = ? where item_id = ?");
						query.setParameter(0, newcount);
						query.setParameter(1, Integer.parseInt(s.split("#")[0]));
						int result = query.executeUpdate();
						if(result == 1){
							flag = true;
						}else{
							flag = false;
						}
					}else{
						flag = false;
					}
				}
			}
			if(flag){
				tr.commit();
			}else{
				tr.rollback();
			}
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
			tr.rollback();
		}finally{
			session.close();
			
		}
		return flag;
	}

	@Override
	public boolean saveLocation(ItemLocation location, int rem) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tr = null;
		boolean flag = false;
		try{
			session = getSessionFactory().openSession();
			tr = session.beginTransaction();
			Serializable serializable = session.save(location);
			if(null != serializable){
				Query query = session.createQuery("update Item set purchased = ?, instock = ? where item_id = ?");
				query.setParameter(0, rem);
				query.setParameter(1, location.getItem_quant());
				query.setParameter(2, location.getItem_id());
				int r = query.executeUpdate();
				if( r == 1){
					flag = true;
				}
			}
			
			if(flag){
				tr.commit();
			}else{
				tr.rollback();
			}
		}catch(Exception e){
			e.printStackTrace();
			tr.rollback();
		}finally{
			session.close();
		}
		return flag;
	}

	@Override
	public Map<String, Object> getReport() throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Map<String, Object> map = new HashMap<>();
		try{
			session = getSessionFactory().openSession();
			Query q1 = session.createQuery("from StockCategory");
			List<StockCategory> list = (List<StockCategory>) q1.list();
			if(list.size() > 0){
				map.put("cats", list);
			}
			Query q2 = session.createQuery("from Item");
			List<Item> items = (List<Item>) q2.list();
			if(items.size() > 0){
				map.put("items", items);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		return null;
	}

	@Override
	public List<StockCategory> getStockCatListWithCount() throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		try{
			session = getSessionFactory().openSession();
			Query query = session.createQuery("select sc.cat_id as cat_id,sc.cat_name as cat_name,sum(purchased+instock+sold+expired) as count from StockCategory sc, Item i where sc.cat_id = i.cat_id group by sc.cat_id,sc.cat_name").setResultTransformer(Transformers.aliasToBean(StockCategory.class));
			List<StockCategory> list = query.list();
			if(list.size() > 0){
				return list;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return null;
	}

	@Override
	public List<Item> itemsBySearch(int parseInt, String itemname) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		try{
			session = getSessionFactory().openSession();
			String hql = "from Item where item_id is not null ";
			if(0 != parseInt){
				hql = hql + "and cat_id = "+parseInt+" ";
			}
			
			if(itemname != null && ""!= itemname){
				hql = hql + "and upper(item_name) like '%"+itemname.toUpperCase()+"%' ";
			}
			
			Query query = session.createQuery(hql);
			List<Item> list = query.list();
			if(list.size() > 0){
				return list;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}

	@Override
	public boolean userbuy(int itemid, Integer userid, int remaining, int ibought, int newsold) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tr = null;
		boolean flag = false;
		try{
			session = getSessionFactory().openSession();
			tr = session.beginTransaction();
			UserBuy buy = new UserBuy();
			buy.setItem_id(itemid);
			buy.setQuantity(ibought);
			buy.setUser_id(userid);
			Serializable serializable = session.save(buy);
			if(null != serializable){
				Query query = session.createQuery("update Item set instock = ?, sold = ? where item_id = ?");
				query.setParameter(0, remaining);
				query.setParameter(1, newsold);
				query.setParameter(2, itemid);
				int j = query.executeUpdate();
				if(j > 0){
					flag = true;
				}
			}
			
			if(flag){
				tr.commit();
			}else{
				tr.rollback();
			}
		}catch(Exception e){
			e.printStackTrace();
			tr.rollback();
		}finally {
			session.close();
		}
		return flag;
	}
	
	

}
