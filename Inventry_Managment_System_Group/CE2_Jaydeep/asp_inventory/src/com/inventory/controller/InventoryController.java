package com.inventory.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.inventory.dao.CommonDao;
import com.inventory.model.Item;
import com.inventory.model.ItemLocation;
import com.inventory.model.MainForm;
import com.inventory.model.QuotationMaster;
import com.inventory.model.StockCategory;
import com.inventory.model.UserRegistration;

@Controller
@RequestMapping(value = "inventory")
public class InventoryController {

	@Autowired
	private CommonDao commonDao;
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	
	@RequestMapping("/dashboard")
	public String dashboard(HttpServletRequest request) throws Exception{
		request.setAttribute("filename", "dashboard");
		return "common/commonHomePage";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request) throws Exception{
		request.setAttribute("filename", "login");
		return "common/commonHomePage";
	}
	
	@RequestMapping("/registration")
	public String registration(HttpServletRequest request) throws Exception{
		request.setAttribute("filename", "registration");
		return "common/commonHomePage";
	}
	
	@RequestMapping("/saveRegistration")
	public String saveRegistration(HttpServletRequest request, @ModelAttribute("mainForm") MainForm form, RedirectAttributes attributes) throws Exception{
		UserRegistration registration = form.getRegistration();
		boolean flag = commonDao.save(registration);
		if(flag){
			attributes.addFlashAttribute("success", "Registration Successfull !!!");
		}else{
			attributes.addFlashAttribute("error", "Some error occured. Try again later.");
		}
		
		return "redirect:registration.htm";
	}
	
	@RequestMapping("/logincheck")
	public String doLogin(HttpServletRequest request) throws Exception{
		try{
			System.out.println("Here");
			HttpSession session = request.getSession();
			String emailid = request.getParameter("emailid");
			String password = request.getParameter("password");

			UserRegistration user = commonDao.getLoginDetails(emailid, password);
			if(null != user){
				session.setAttribute("userdtls", user);
				if(user.getRole().equalsIgnoreCase("admin")){
					request.setAttribute("filename", "homePage");
					return "userpage/userCommonPage";
				}else{
					request.setAttribute("filename", "userHomePage");
					return "normalusers/commonpage";
				}
			}else{
				request.setAttribute("error", "Please provide correct email and password !!!");
				request.setAttribute("filename", "login");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "common/commonHomePage";
	}
	
	@RequestMapping("/addCategory")
	public String addCategory(HttpServletRequest request) throws Exception{
		System.out.println("add cats");
		request.setAttribute("filename", "AddCategory");
		return "userpage/userCommonPage";
	}
	
	@RequestMapping("/saveAddCat")
	public String saveAddCat(HttpServletRequest request, RedirectAttributes attributes) throws Exception{
		String name = request.getParameter("catname");
		StockCategory category = new StockCategory();
		category.setCat_name(name);
		boolean flag = commonDao.save(category);
		if(flag){
			attributes.addFlashAttribute("success", "Successfully saved !!!");
		}else{
			attributes.addFlashAttribute("error","Try again later !!!");
		}
		return "redirect:addCategory.htm";
	}
	
	@RequestMapping("/addItem")
	public String addItem(HttpServletRequest request) throws Exception{
		List<StockCategory> cats = commonDao.getListByRistrictions(StockCategory.class, null, "cat_name");
			request.setAttribute("cats", cats);
		request.setAttribute("filename", "AddStock");
		return "userpage/userCommonPage";
	}
	
	@RequestMapping("/saveItem")
	public String saveItem(HttpServletRequest request, RedirectAttributes attributes) throws Exception{
		try{
			String catid = request.getParameter("catid");
			String name = request.getParameter("itemname");
			Item item = new Item();
			item.setCat_id(Integer.parseInt(catid));
			item.setItem_name(name);
			item.setInstock(0);
			item.setExpired(0);
			item.setPurchased(0);
			item.setSold(0);
			boolean flag = commonDao.save(item);
			if(flag){
				attributes.addFlashAttribute("success","Item succesfully saved !!!");
			}else{
				attributes.addFlashAttribute("error", "Error while saving. Try again later.");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "redirect:addItem.htm";
	}
	
	@RequestMapping("/addStock")
	public String addStock(HttpServletRequest request) throws Exception{
		List<Item> items = commonDao.getListByRistrictions(Item.class, null, "item_name");
		request.setAttribute("items", items);
		request.setAttribute("filename", "AddQuotation");
		return "userpage/userCommonPage";
	}
	
	@RequestMapping("/saveQuotation")
	public String saveQuotation(HttpServletRequest request, @ModelAttribute("mainForm") MainForm form, RedirectAttributes attributes) throws Exception{
		String quotenum = request.getParameter("quotenum");
		String quotedate = request.getParameter("quotedate");
		String gtotal = request.getParameter("grandtotal");
		List<String> data = form.getMaster().getData();
		QuotationMaster master = new QuotationMaster();
		master.setQuotation_number(quotenum);
		master.setTotal_amount(Integer.parseInt(gtotal));
		master.setQuotation_date(format.parse(quotedate));
		boolean flag = commonDao.saveQuotation(master, data);
		if(flag){
			attributes.addFlashAttribute("success", "Quotation succesfully added.");
		}else{
			attributes.addFlashAttribute("error", "Error while saving data !!!");
		}
		return "redirect:addStock.htm";
	}
	
	@RequestMapping("/loadAssignItemLocation")
	public String loadAssignItemLocation(HttpServletRequest request) throws Exception{
		request.setAttribute("filename", "assignItemLocation");
		List<StockCategory> list = commonDao.getListByRistrictions(StockCategory.class, null, null);
		request.setAttribute("list", list);
		return "userpage/userCommonPage";
	}
	
	@RequestMapping("/getItemsByCategory")
	public void getItemsByCategory(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String id = request.getParameter("id");
		Map<String, Object> map = new HashMap<>();
		map.put("cat_id", Integer.parseInt(id));
		List<Item> list = commonDao.getListByRistrictions(Item.class, map, "item_name");
		String data = "<option value='select'>--Select--</option>";
		for(Item i : list){
			data = data + "<option value='"+i.getItem_id()+"'>" + i.getItem_name() + "</option>";
		}
		response.getWriter().print(data);
	}
	
	@RequestMapping("/getItemsPurchased")
	public void getItemsPurchased(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String id = request.getParameter("id");
		Map<String, Object> map = new HashMap<>();
		map.put("item_id", Integer.parseInt(id));
		List<Item> list = (List<Item>)commonDao.getListByRistrictions(Item.class, map, "item_name");
		response.getWriter().print(list.get(0).getPurchased());
	}
	
	@RequestMapping("/saveLocation")
	public String saveLocation(HttpServletRequest request , @ModelAttribute("mainForm") MainForm form, RedirectAttributes attributes) throws Exception{
		ItemLocation location = form.getLocation();
		int total = Integer.parseInt(request.getParameter("totali"));
		int rem = total - location.getItem_quant();
		boolean flag = commonDao.saveLocation(location, rem);
		if(flag){
			attributes.addFlashAttribute("success","Location successfully assigned");
		}else{
			attributes.addFlashAttribute("error", "Error occured while assigning location. Try again later.");
		}
		return "redirect:loadAssignItemLocation.htm";
	}
	
	@RequestMapping("/loadStockReport")
	public String loadStockReport(HttpServletRequest request) throws Exception{
		List<StockCategory> list = commonDao.getStockCatListWithCount();
		request.setAttribute("cats", list);
		request.setAttribute("filename", "stockReports");
		return "userpage/userCommonPage";
	}
	
	@RequestMapping("/loadItemReport")
	public String loadItemReport(HttpServletRequest request) throws Exception{
		Map<String, Object> map = new HashMap<>();
		map.put("cat_id", Integer.parseInt(request.getParameter("id")));
		List<Item> items = commonDao.getListByRistrictions(Item.class, map, null);
		request.setAttribute("items", items);
		request.setAttribute("filename", "itemReports");
		return "userpage/userCommonPage";
	}
	
	@RequestMapping("/loadSearch")
	public String loadSearch(HttpServletRequest request) throws Exception{
		List<StockCategory> cats = commonDao.getListByRistrictions(StockCategory.class, null, "cat_name");
		request.setAttribute("cats", cats);
		List<Item> items = commonDao.getListByRistrictions(Item.class, null, "item_name");
		request.setAttribute("items", items);
		request.setAttribute("filename", "userSearch");
		return "normalusers/commonpage";
	}
	
	@RequestMapping("/search")
	public String search(HttpServletRequest request) throws Exception{
		
		String catid = request.getParameter("catid");
		String itemname = request.getParameter("itemname");
		List<Item> items = commonDao.itemsBySearch(Integer.parseInt(catid), itemname);
		if(null != items && items.size() > 0){
			request.setAttribute("items", items);
		}
		List<StockCategory> cats = commonDao.getListByRistrictions(StockCategory.class, null, "cat_name");
		request.setAttribute("cats", cats);
		request.setAttribute("filename", "userSearch");
		return "normalusers/commonpage";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		if(null != session){
			session.removeAttribute("userdtls");
			session.invalidate();
		}
		return "redirect:dashboard.htm";
	}
	
	@RequestMapping("/userBuy")
	public String userBuy(HttpServletRequest request, RedirectAttributes attributes) throws Exception{
		HttpSession session = request.getSession();
		String itemid = request.getParameter("itemid");
		String instock = request.getParameter("instock");
		String ibought = request.getParameter("ibought");
		String sold = request.getParameter("sold");
		UserRegistration registration = (UserRegistration) session.getAttribute("userdtls");
		int remaining = Integer.parseInt(instock) - Integer.parseInt(ibought);
		int newsold = Integer.parseInt(ibought) + Integer.parseInt(sold);
		boolean flag = commonDao.userbuy(Integer.parseInt(itemid), registration.getUserid(), remaining, Integer.parseInt(ibought), newsold);
		if(flag){
			attributes.addFlashAttribute("success", "Item purchased successfully");
		}else{
			attributes.addFlashAttribute("error", "Error occured while purchasing item. Try again later !!!");
		}
		return "redirect:loadSearch.htm";
	}
	
}
