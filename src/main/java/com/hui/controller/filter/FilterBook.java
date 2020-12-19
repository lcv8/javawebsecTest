package com.hui.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilterBook implements Filter {
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
        if(req.getSession().getAttribute("loginUsers") != null){
            resp.sendRedirect(req.getAttribute("basePath")+"/jsp/main.jsp");
        } else {
            filterChain.doFilter(req,resp);
        }
    }

    @Override
    public void destroy() {

    }
}
