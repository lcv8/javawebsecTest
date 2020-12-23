package com.hui.service;

import com.hui.entity.BookInfo;
import com.hui.entity.Page;

public interface BaseService {
	Page getBookPage(Page page, BookInfo book);
}
