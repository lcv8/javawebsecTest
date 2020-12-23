package com.hui.controller;

import com.hui.entity.BookInfo;
import com.hui.entity.BookType;
import com.hui.entity.Page;
import com.hui.service.BaseService;
import com.hui.service.BookService;
import com.hui.service.impl.BaseServiceImpl;
import com.hui.service.impl.BookServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

public class BookController {
    private BookService service = new BookServiceImpl();
    private BaseService baseService = new BaseServiceImpl();

    public void borrow(HttpServletRequest req , HttpServletResponse resp) throws Exception {
        String bookinfoid = req.getParameter("id");
        String borrow = req.getParameter("borrow");
        boolean flag = true;
        if(bookinfoid!=null && !"".equals(bookinfoid)&& borrow!=null && !"".equals(borrow)) {
            this.service.updateBookInfo(new BookInfo()
                    .setBookId(Integer.valueOf(bookinfoid))
                    .setIsBorrow(Integer.valueOf(borrow)));
            flag = true;
        }else {
            flag = false;
        }
        //AJAX响应
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.write(String.valueOf(flag));
        out.flush();
        out.close();
    }
    public void addBooks(HttpServletRequest req , HttpServletResponse resp) {

    }
    public void updateBooks(HttpServletRequest req , HttpServletResponse resp) throws Exception {
        BookInfo bookinfo = this.getRequestBookInfo(req);
        Page page = this.getRequestPage(req);
        String isBorrowUpdate = req.getParameter("isBorrowUpdate");

        //改数据
        int i = service.updateBookInfo(new BookInfo()
                .setIsBorrow(Integer.valueOf(isBorrowUpdate))
                .setBookId(bookinfo.getBookId()));
        //组装重定向的参数
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
        if(page.getPageNumber()!=null) {
            url.append("&pageNumber="+page.getPageNumber());
        }

        resp.sendRedirect(url.toString());

    }
    //获取bookinfo
    private BookInfo getRequestBookInfo(HttpServletRequest req) {
        //要考虑到第一次抵达list页面什么都没有的情况
        String bookIdStr = req.getParameter("bookId");
        Integer bookId = null;
        if(bookIdStr!=null && !bookIdStr.equals("")) {
            bookId = Integer.valueOf(bookIdStr);
        }
        String booktypeStr = req.getParameter("booktype");
        if(booktypeStr == null || booktypeStr.equals("")) {
            booktypeStr = "-1";
        }
        Integer bookTypeId = Integer.valueOf(booktypeStr);
        String isBorrowStr = req.getParameter("isBorrow");
        if(isBorrowStr == null || isBorrowStr.equals("")) {
            isBorrowStr = "-1";
        }
        Integer isBorrow = Integer.valueOf(isBorrowStr);
        BookInfo bookinfo = new BookInfo()
                .setBookId(bookId)
                .setBookTypeId(bookTypeId==-1 ? null : bookTypeId)
                .setBookName(req.getParameter("bookname"))
                .setIsBorrow(isBorrow == -1 ? null : isBorrow);
        return bookinfo;
    }
    //获取page
    private Page getRequestPage(HttpServletRequest req) {
        //1.当刚刚登入首次抵达首页，这个时候有可能没有pageNumber这个参数
        String pageNumberStr = req.getParameter("pageNumber");
        Integer pageNumber = 1;
        //2.有传入的pageNumber用传入的，没有的话用默认值
        if(pageNumberStr != null && !"".equals(pageNumberStr)) {
            pageNumber = Integer.valueOf(pageNumberStr);
        }
        Page page = new Page()
                .setPageNumber(pageNumber);
        //把page其他的值算出来

        return page;
    }
    //取列表展示页
    public void toListBooks(HttpServletRequest req , HttpServletResponse resp) throws Exception{
        BookInfo bookinfo = this.getRequestBookInfo(req);
        Page page = this.getRequestPage(req);
        page = this.baseService.getBookPage(page, bookinfo);
        //图书类型
        List<BookType> types = this.service.queryBookType(null);
        //图书列表
        List<BookInfo> books = this.service.queryBookInfo(bookinfo, page);

        req.setAttribute("types", types);
        req.setAttribute("bookinfoQueryPar", bookinfo);
        req.setAttribute("books", books);
        req.setAttribute("page", page);
        //查出来的数据转发到页面
        req.getRequestDispatcher("/jsp/main.jsp").forward(req, resp);
    }
}
