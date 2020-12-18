package com.hui.controller;

import com.hui.dao.UserDao;
import com.hui.dao.impl.UsersDaoImpl;
import com.hui.entity.Users;
import com.hui.service.UserService;
import com.hui.service.impl.UserServiceImpl;
import com.hui.util.CheckUsesMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UserController {
    private UserService service = new UserServiceImpl();

    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Users users = new Users()
                .setUserCode(req.getParameter("username"))
                .setPassword(req.getParameter("password"));
        req.setAttribute("users",users);
        String code = service.checkUsers(users,"login");
        switch (code) {
            case "100":
                Users user = service.queryUser(users).get(0);
                System.out.println(user);
                req.getSession().setAttribute("loginUsers",user);
                resp.sendRedirect(req.getAttribute("basePath")+"/books/toListBooks.do");
                return;
            case "105":
                req.setAttribute("message", CheckUsesMessage.MSG_105);
                req.getRequestDispatcher("/jsp/login.jsp").forward(req,resp);
                return;
            default:
                return;
        }
    }

    public void regiest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Users users = new Users()
                .setUserCode(req.getParameter("username"))
                .setPassword(req.getParameter("password"))
                .setGender(Integer.valueOf(req.getParameter("sex")))
                .setEmail(req.getParameter("email"));
        req.setAttribute("users",users);
        String code = service.checkUsers(users,"reg");
        switch (code) {
            case "100":
                service.insertUser(users);
                resp.sendRedirect(req.getAttribute("basePath")+"/jsp/login.jsp");
                return;
            case "101":
                req.setAttribute("message", CheckUsesMessage.MSG_101);
                req.getRequestDispatcher("/jsp/reg.jsp").forward(req,resp);
                return;
            case "102":
                req.setAttribute("message", CheckUsesMessage.MSG_102);
                req.getRequestDispatcher("/jsp/reg.jsp").forward(req,resp);
                return;
            case "103":
                return;
            case "104":
                req.setAttribute("message", CheckUsesMessage.MSG_104);
                req.getRequestDispatcher("/jsp/reg.jsp").forward(req,resp);
                return;
            default:
                return;
        }
    }

    //验证用户名炒作
    public void regiestCheckUsernameAjax(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        List<Users> list = this.service.queryUser(new Users().setUserCode(username));
        boolean flag = true;
        if (list.size() == 0) {
            flag = true;
        } else {
            flag = false;
        }
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(String.valueOf(flag));
        writer.flush();
    }

    public void logout(HttpServletRequest req, HttpServletResponse resp) {
    }

    public void update(HttpServletRequest req, HttpServletResponse resp) {
    }
}
