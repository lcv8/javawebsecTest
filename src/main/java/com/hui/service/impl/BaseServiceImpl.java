package com.hui.service.impl;

import java.util.List;

import com.hui.dao.BookDao;
import com.hui.dao.UserDao;
import com.hui.dao.impl.BooksDaoImpl;
import com.hui.dao.impl.UsersDaoImpl;
import com.hui.entity.BookInfo;
import com.hui.entity.Page;
import com.hui.service.BaseService;

public class BaseServiceImpl implements BaseService{
	private BookDao bookDao = new BooksDaoImpl();
	private UserDao usersDao = new UsersDaoImpl();
	@Override
	public Page getBookPage(Page page,BookInfo book) {
		List<BookInfo> books = bookDao.queryBookInfo(book, null);
		Integer count = books.size();
		//1.pageCount
		Integer pageCount = 1;
		if(count % page.getSize() != 0) {
			pageCount = (count / page.getSize()) + 1;
		}else {
			pageCount = (count / page.getSize());
		}
		//2.index/offset
		Integer index = (page.getPageNumber() - 1) * page.getSize();
		
		page.setCount(count);
		page.setPageCount(pageCount);
		page.setIndex(index);
		
		return page;
	}
}
