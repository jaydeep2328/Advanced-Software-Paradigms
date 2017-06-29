package Filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionFilter implements Filter 
{
	private ArrayList<String> urlList;

	@Override
	public void destroy() 
	{
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException 
	{
		
		
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		String url = request.getServletPath();
		boolean allowedRequest = true;
		if(urlList.contains(url))
		{
			allowedRequest = false;
		}
		if(allowedRequest)
		{
			HttpSession session = request.getSession(false);
			if(null == session && !url.contains("/admin/"))
			{
				response.sendRedirect("homePage.htm");
			}else
			{
				arg2.doFilter(arg0, arg1);
			}
		}
		else
		{
		arg2.doFilter(arg0, arg1);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException 
	{
		String urls = arg0.getInitParameter("avoid-urls");
		StringTokenizer token = new StringTokenizer(urls,",");
		urlList = new ArrayList<>();
		while(token.hasMoreTokens())
		{
			urlList.add(token.nextToken());
		}
	}

}
