package com.hui.service.impl;

import com.hui.dao.BookDao;
import com.hui.dao.impl.BooksDaoImpl;
import com.hui.entity.BookInfo;
import com.hui.entity.BookType;
import com.hui.entity.Page;
import com.hui.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BooksDaoImpl();

    @Override
    public List<BookType> queryBookType(BookType bookType) {
        return bookDao.queryBookType(bookType);
    }

    @Override
    public List<BookInfo> queryBookInfo(BookInfo bookInfo, Page page) {
        return bookDao.queryBookInfo(bookInfo,page);
    }

    @Override
    public int updateBookInfo(BookInfo bookInfo) {
        return bookDao.updateBookInfo(bookInfo);
    }

    @Override
    public int insertBookInfo(BookInfo bookInfo) {
        return bookDao.insertBookInfo(bookInfo);
    }
}
