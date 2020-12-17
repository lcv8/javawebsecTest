package com.hui.service.impl;

import com.hui.entity.BookInfo;
import com.hui.entity.BookType;
import com.hui.entity.Page;
import com.hui.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    @Override
    public List<BookType> queryBookType(BookType bookType) {
        return null;
    }

    @Override
    public List<BookInfo> queryBookInfo(BookInfo bookInfo, Page page) {
        return null;
    }

    @Override
    public int updateBookInfo(BookInfo bookInfo) {
        return 0;
    }

    @Override
    public int insertBookInfo(BookInfo bookInfo) {
        return 0;
    }
}
