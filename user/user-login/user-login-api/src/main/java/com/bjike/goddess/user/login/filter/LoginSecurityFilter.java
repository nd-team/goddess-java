package com.bjike.goddess.user.login.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-28 14:47]
 * @Description: [（验证是否登陆）过滤器]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@WebFilter(filterName = "loginFilter", urlPatterns = {"/*"})
public class LoginSecurityFilter implements Filter {
    private static final String LOGIN_URL = "/login.jsp";
    private static final Logger CONSOLE = LoggerFactory.getLogger(LoginSecurityFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        filterConfig.getServletContext()
        CONSOLE.info("LoginSecurityFilter is init...");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String url = httpRequest.getRequestURI().toString();
        if (true) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        } else {
            httpResponse.sendRedirect(LOGIN_URL);
        }
    }

    @Override
    public void destroy() {

    }
}
