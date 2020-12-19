package com.hui.controller;

import com.hui.entity.BookInfo;
import com.hui.entity.BookType;
import com.hui.entity.Page;
import com.hui.service.BookService;
import com.hui.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author lcv8
 */
public class BookController {
    private BookService service = new BookServiceImpl();

    public void addBooks(HttpServletRequest req, HttpServletResponse resp) {
    }

    public void updateBooks(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BookInfo bookinfo = this.getRequestBookInfo(req);
        Page page = this.getPage();
        String isBorrowUpdate = req.getParameter("isBorrowUpdate");

        //改数据
        int i = service.updateBookInfo(new BookInfo()
                .setIsBorrow(Integer.valueOf(isBorrowUpdate))
                .setBookId(bookinfo.getBookId()));

        StringBuffer url = new StringBuffer(req.getAttribute("basePath")+"/books/toListBooks.do?1=1");
        if(bookinfo.getBookTypeId()!=null) {
            url.append("&booktype="+bookinfo.getBookTypeId());
        }
        if(bookinfo.getBookName()!=null) {
            url.append("&bookname="+bookinfo.getBookName());
        }
        if(bookinfo.getIsBorrow()!=null) {
            url.append("&isBorrow="+bookinfo.getIsBorrow());
        }

        resp.sendRedirect(url.toString());
    }

    private BookInfo getRequestBookInfo(HttpServletRequest req){
        String bookIdStr = req.getParameter("bookId");
        Integer bookId = null;
        if(bookIdStr!=null && !bookIdStr.equals("")) {
            bookId = Integer.valueOf(bookIdStr);
        }
        String booktypeStr = req.getParameter("booktype");
        System.out.println(booktypeStr);
        if(booktypeStr == null || booktypeStr.equals("")) {
            booktypeStr = "-1";
        }
        Integer bookTypeId = Integer.valueOf(booktypeStr);
        String isBorrowStr = req.getParameter("isBorrow");
        if(isBorrowStr == null || isBorrowStr.equals("")) {
            isBorrowStr = "-1";
        }
        Integer isBorrow = Integer.valueOf(isBorrowStr);
        BookInfo bookInfo = new BookInfo()
                .setBookId(bookId)
                    .setBookTypeId(bookTypeId==-1 ? null : bookTypeId)
                        .setBookName(req.getParameter("bookname"))
                            .setIsBorrow(isBorrow == -1 ? null : isBorrow);
        return bookInfo;
    }
    private Page getPage(){
        Page page = new Page();
        return page;
    }
    public void toListBooks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookInfo bookInfo = this.getRequestBookInfo(req);
        Page page = this.getPage();
        List<BookType> types = service.queryBookType(null);
        List<BookInfo> books = service.queryBookInfo(bookInfo, null);
        System.out.println(types);
        req.setAttribute("types",types);
        req.setAttribute("bookinfoQueryPar", bookInfo);
        req.setAttribute("books",books);
        req.getRequestDispatcher("/jsp/main.jsp").forward(req,resp);
    }
}
