package com.hui.controller.filter;

import com.hui.entity.Users;
import com.hui.util.CheckUsesMessage;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilterBooks implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        String path = req.getContextPath();
        String basePath = req.getScheme()
                + "://" + req.getServerName()
                + ":" + req.getServerPort() + path;
        req.setAttribute("basePath",basePath);
        Users users = (Users) req.getSession().getAttribute("loginUsers");
        System.out.println("++++>" + users);
        if(users == null){
            resp.sendRedirect(req.getAttribute("basePath")+"/jsp/login.jsp");
        } else {
            filterChain.doFilter(req,resp);
        }
    }

    @Override
    public void destroy() {

    }
}
