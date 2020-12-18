package com.hui.controller;

import com.hui.entity.BookInfo;
import com.hui.entity.BookType;
import com.hui.service.BookService;
import com.hui.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookController {
    private BookService bookService = new BookServiceImpl();

    public void addBooks(HttpServletRequest req, HttpServletResponse resp) {
    }

    public void updateBooks(HttpServletRequest req, HttpServletResponse resp) {
    }

    public void toListBooks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BookType> types = bookService.queryBookType(null);
        List<BookInfo> books = bookService.queryBookInfo(null, null);
        System.out.println(books);
        req.setAttribute("types",types);
        req.setAttribute("books",books);
        req.getRequestDispatcher("/jsp/main.jsp").forward(req,resp);
    }
}
