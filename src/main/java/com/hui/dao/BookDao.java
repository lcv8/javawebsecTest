package com.hui.dao;

import com.hui.entity.BookInfo;
import com.hui.entity.BookType;
import com.hui.entity.Page;

import java.util.List;

/**
 * @author lcv8
 */
public interface BookDao {
    List<BookType> queryBookType(BookType bookType);
    List<BookInfo> queryBookInfo(BookInfo bookInfo, Page page);
    int updateBookInfo(BookInfo bookInfo);
    int insertBookInfo(BookInfo bookInfo);
}
