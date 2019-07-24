package com.byzx.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class CodeFilter
 */
@WebFilter("/CodeFilter")
public class CodeFilter implements Filter {
	public static List<String> pattenURL = new ArrayList<String>();
    public CodeFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpServletRequest req = (HttpServletRequest) request;  
	    HttpServletResponse resp = (HttpServletResponse) response;  
	    Object obj= req.getSession().getAttribute("USER");
	    String local = req.getServletPath();
		if(obj==null) {
			for (String url : pattenURL) {
		            if (local.equals(url)) {
		                    chain.doFilter(request, response);
	                    return;
	                }
			}
			resp.sendRedirect("../pages/login.jsp");
		}else {
			chain.doFilter(request, response);
		} 
	}
	public void init(FilterConfig fConfig) throws ServletException {
				pattenURL.add("/pages/login.jsp");// 登录jsp
				pattenURL.add("/pages/image.jsp");// 登录验证码
				pattenURL.add("/user/validCode.action");// 验证码返回
		        pattenURL.add("/user/isAvailableUser.action");// 账号验证
			 	pattenURL.add("/static/css/login.css");// 
		        pattenURL.add("/static/css/bootstrap.min.css");// 
		        pattenURL.add("/static/js/jquery.min.js");// 
		        pattenURL.add("/static/js/jquery.cookie.js");// 
		        pattenURL.add("/static/js/pages/login.jsp");// 
		        pattenURL.add("/user/index.action");// 
		       
		        
		        
	}

}
