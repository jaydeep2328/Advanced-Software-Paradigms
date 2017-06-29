package com.inventory.utility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class Interceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		String url = request.getServletPath();
		if(url.contains("/admin/") && !"/admin/loginCheck.htm".contains(url) && !"/admin/adminLogin.htm".contains(url)) {
			HttpSession session = request.getSession(false);
			if(session == null || session.getAttribute("email") == null) {
				response.sendRedirect("adminLogin.htm?attr=logout");
				return false;
			}else {
				return true;
			}
		}else {
			if(request.getAttribute("attr") != null)request.removeAttribute("attr");
			return true;	
		}
		
		
	}
	

}
