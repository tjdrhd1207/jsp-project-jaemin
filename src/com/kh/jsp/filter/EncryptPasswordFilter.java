package com.kh.jsp.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.kh.jsp.wrapper.LoginWrapper;

/**
 * Servlet Filter implementation class EncryptPasswordFilter
 */
@WebFilter("*.me")
public class EncryptPasswordFilter implements Filter {

    /**
     * Default constructor. 
     */
    public EncryptPasswordFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		System.out.println("암호화 필터!");
		
		HttpServletRequest	hr = (HttpServletRequest) request;
		
		//wrapper는 결국 request 
		LoginWrapper lw = new  LoginWrapper(hr);
		
		
		
		//우리가 전달한 request는
		// pass the request along the filter chain
		chain.doFilter(lw, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
