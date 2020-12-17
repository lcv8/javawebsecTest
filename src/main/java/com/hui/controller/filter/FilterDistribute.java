package com.hui.controller.filter;


import com.hui.controller.BookController;
import com.hui.controller.UserController;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilterDistribute implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)  {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        String path = req.getContextPath();
        String basePath = req.getScheme()
                + "://" + req.getServerName()
                + ":" + req.getServerPort() + path;
        req.setAttribute("basePath",basePath);
        try{
            req.setCharacterEncoding("UTF-8");
            //uri
            String uri = req.getRequestURI();
            UserController usersController = new UserController();
            BookController booksController = new BookController();
            if(uri.endsWith("/users/login.do")) {
                usersController.login(req, resp);
            }else if(uri.endsWith("/users/regiest.do")) {
                usersController.regiest(req, resp);
            }else if(uri.endsWith("/users/regiestCheckUsernameAjax.do")) {
                usersController.regiestCheckUsernameAjax(req, resp);
            }else if(uri.endsWith("/users/logout.do")) {
                usersController.logout(req, resp);
            }else if(uri.endsWith("/users/update.do")) {
                usersController.update(req, resp);
            }else if(uri.endsWith("/books/addBooks.do")) {
                booksController.addBooks(req, resp);
            }else if(uri.endsWith("/books/updateBooks.do")) {
                booksController.updateBooks(req, resp);
            }else if(uri.endsWith("/books/toListBooks.do")) {
                booksController.toListBooks(req, resp);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void destroy() {

    }
}
