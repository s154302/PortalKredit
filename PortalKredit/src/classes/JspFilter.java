package classes;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JspFilter implements Filter{
	@Override
	  public void  doFilter(ServletRequest request, ServletResponse response,                
	           FilterChain chain) throws IOException, ServletException {
		  HttpServletRequest req= (HttpServletRequest) request;
		  req.getSession().invalidate();
			((HttpServletResponse) response).sendRedirect(req.getContextPath()+"/index");
			
	    
	  }

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	}