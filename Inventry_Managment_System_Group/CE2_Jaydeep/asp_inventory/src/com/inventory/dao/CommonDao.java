package com.inventory.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.inventory.model.Item;
import com.inventory.model.ItemLocation;
import com.inventory.model.QuotationMaster;
import com.inventory.model.StockCategory;
import com.inventory.model.UserRegistration;

public interface CommonDao {

	<T> Boolean save(T t) throws Exception;
	
	UserRegistration getLoginDetails(String emailid, String password) throws Exception;
	
	<T> List<T> getListByRistrictions(Class<T> c, Map<String, Object> map, String orderBy);

	<T> List<T> getListByRistrictions(Class<T> c, Map<String, Object> map, String orderBy, String orderType, Integer firstIndex, Integer maxResult);
	<T> List<T> getListByRistrictionsWithProjections(Class<T> c, String[] columns, Map<String, Object> map, String orderBy, String orderType, Integer firstIndex, Integer maxResult);

	<T> T get(Class<T> c, Serializable id);

	boolean saveQuotation(QuotationMaster master, List<String> data) throws Exception;

	boolean saveLocation(ItemLocation location, int rem) throws Exception;

	Map<String, Object> getReport() throws Exception;

	List<StockCategory> getStockCatListWithCount() throws Exception;

	List<Item> itemsBySearch(int parseInt, String itemname) throws Exception;

	boolean userbuy(int itemid, Integer userid, int remaining, int ibought, int newsold) throws Exception;
}
