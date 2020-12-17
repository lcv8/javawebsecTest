package com.hui.service;

import com.hui.entity.BookInfo;
import com.hui.entity.BookType;
import com.hui.entity.Page;

import java.util.List;

public interface BookService {
    List<BookType> queryBookType(BookType bookType);
    List<BookInfo> queryBookInfo(BookInfo bookInfo, Page page);
    int updateBookInfo(BookInfo bookInfo);
    int insertBookInfo(BookInfo bookInfo);
}
