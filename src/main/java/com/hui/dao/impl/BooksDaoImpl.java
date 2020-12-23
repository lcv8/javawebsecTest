package com.hui.dao.impl;

import com.hui.dao.BaseDao;
import com.hui.dao.BookDao;
import com.hui.entity.BookInfo;
import com.hui.entity.BookType;
import com.hui.entity.Page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BooksDaoImpl extends BaseDao implements BookDao {
    @Override
    public List<BookType> queryBookType(BookType bookType) {
        ArrayList<BookType> bookTypes = new ArrayList<>();
        try {
            Connection conn = super.getConn();
            StringBuffer sql = new StringBuffer("SELECT * FROM `book_type` ");
            PreparedStatement statement = conn.prepareStatement(String.valueOf(sql));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                BookType typeTmpe = new BookType();
                typeTmpe.setBookTypeId(resultSet.getInt("id"));
                typeTmpe.setBookTypeName(resultSet.getString("type_name"));
                bookTypes.add(typeTmpe);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            super.closeConn();
        }
        return bookTypes;
    }

    @Override
    public List<BookInfo> queryBookInfo(BookInfo bookInfo, Page page) {
        ArrayList<BookInfo> bookTypes = new ArrayList<>();
        try {
            Connection conn = super.getConn();
            StringBuffer sql = new StringBuffer("SELECT ");
            sql.append(" bif.book_id bif_id , bif.book_code bif_code , ");
            sql.append(" bif.book_name bif_name , bif.book_type bif_type , ");
            sql.append(" bif.book_author bif_author , bif.publish_press bif_press , ");
            sql.append(" bif.publish_date bif_publish_date , bif.is_borrow bif_is_borrow , ");
            sql.append(" bif.createdBy bif_createdBy , ");
            sql.append(" bt.id bt_id , bt.type_name bt_name  ");
            sql.append(" FROM ");
            sql.append(" book_info bif , book_type bt ");
            sql.append(" WHERE  ");
            sql.append(" bif.book_type = bt.id ");

            if(bookInfo != null){
                if(bookInfo.getBookId() != null){
                    sql.append(" AND bif.book_id = ? ");
                }
                if(bookInfo.getBookTypeId() != null){
                    sql.append(" AND bif.book_type = ? ");
                }
                if(bookInfo.getBookName() != null){
                    sql.append(" AND bif.book_name LIKE concat( '%',?,'%' ) ");
                }
                if(bookInfo.getIsBorrow() != null){
                    sql.append(" AND bif.is_borrow = ? ");
                }
            }
            sql.append(" ORDER BY bif.book_code");
            if(page != null){
                sql.append(" LIMIT ? , ? ");
            }
            PreparedStatement statement = conn.prepareStatement(String.valueOf(sql));
            int index = 0;
            if(bookInfo != null){
                if(bookInfo.getBookId() != null){
                    statement.setObject(++index,bookInfo.getBookId());
                }
                if(bookInfo.getBookTypeId() != null){
                    statement.setObject(++index,bookInfo.getBookTypeId());
                }
                if(bookInfo.getBookName() != null){
                    statement.setObject(++index,bookInfo.getBookName());
                }
                if(bookInfo.getIsBorrow() != null){
                    statement.setObject(++index,bookInfo.getIsBorrow());
                }
            }
            if(page != null){
                statement.setObject(++index,page.getIndex());
                statement.setObject(++index,page.getSize());
            }
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                BookInfo bif = new BookInfo();
                bif.setBookId(rs.getInt("bif_id"));
                bif.setBookCode(rs.getString("bif_code"));
                bif.setBookName(rs.getString("bif_name"));
                bif.setBookTypeId(rs.getInt("bif_type"));
                bif.setBookAuthor(rs.getString("bif_author"));
                bif.setPublishPress(rs.getString("bif_press"));
                bif.setPublishDate(rs.getDate("bif_publish_date"));
                bif.setIsBorrow(rs.getInt("bif_is_borrow"));
                bif.setCreateBy(rs.getInt("bif_createdBy"));
                BookType type = new BookType();
                type.setBookTypeName(rs.getString("bt_name"));
                type.setBookTypeId(rs.getInt("bif_type"));
                bif.setBookType(type);
                bookTypes.add(bif);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            super.closeConn();
        }
        return bookTypes;
    }

    @Override
    public int updateBookInfo(BookInfo bookInfo) {
        try {
            Connection conn = super.getConn(false);
            StringBuffer sql = new StringBuffer();
            sql.append(" INSERT INTO `book_info`(`book_code` , `book_name` , `book_type` , `book_author` , `publish_press` , `publish_date` , `createBy`) ");
            sql.append(" VALUES ( ? , ? , ? , ? , ? , ? , ? ) ");
            PreparedStatement statement = conn.prepareStatement(sql.toString());
            statement.setObject(1,bookInfo.getBookCode());
            statement.setObject(2,bookInfo.getBookName());
            statement.setObject(3,bookInfo.getBookType());
            statement.setObject(4,bookInfo.getBookAuthor());
            statement.setObject(2,bookInfo.getPublishPress());
            statement.setObject(3,bookInfo.getPublishDate());
            statement.setObject(4,bookInfo.getCreateBy());
            int i = statement.executeUpdate();
            if(i > 0){
                super.commit();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            super.rollback();
        } finally {
            super.closeConn();
        }

        return 0;
    }

    @Override
    public int insertBookInfo(BookInfo bookInfo) {
        try {
            Connection conn = super.getConn();
            StringBuffer sql = new StringBuffer(" UPDATE `book_info` SET ");
            if(bookInfo != null){
                if(bookInfo.getBookName() != null){
                    sql.append(" book_name = ? , ");
                }
                if(bookInfo.getBookType() != null){
                    sql.append(" book_type = ? , ");
                }
                if(bookInfo.getBookAuthor() != null){
                    sql.append(" book_author = ? , ");
                }
                if(bookInfo.getIsBorrow() != null){
                    sql.append(" is_borrow = ? , ");
                }
                if(bookInfo.getPublishPress() != null){
                    sql.append(" publish_press = ? , ");
                }
                if(bookInfo.getPublishDate() != null){
                    sql.append(" publish_date = ? , ");
                }
            }
            sql.append(" last_updatetime = ? ");
            sql.append(" where 1 = 1 ");
            if(bookInfo != null){
                if(bookInfo.getBookId() != null){
                    sql.append(" AND book_id = ? ");
                }
            }
            PreparedStatement statement = conn.prepareStatement(sql.toString());
            int index = 0;
            if(bookInfo != null){
                if(bookInfo.getBookName() != null){
                    statement.setObject(++index,bookInfo.getBookName());
                }
                if(bookInfo.getBookType() != null){
                    statement.setObject(++index,bookInfo.getBookType());
                }
                if(bookInfo.getBookAuthor() != null){
                    statement.setObject(++index,bookInfo.getBookAuthor());
                }
                if(bookInfo.getIsBorrow() != null){
                    statement.setObject(++index,bookInfo.getIsBorrow());
                }
                if(bookInfo.getPublishPress() != null){
                    statement.setObject(++index,bookInfo.getPublishPress());
                }
                if(bookInfo.getPublishDate() != null){
                    statement.setObject(++index,bookInfo.getPublishDate());
                }
            }
            statement.setObject(++index,new Date());
            if(bookInfo != null){
                if(bookInfo.getBookId() != null){
                    statement.setObject(++index,bookInfo.getBookId());
                }
            }
            int i = statement.executeUpdate();
            if(i > 0){
                super.commit();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            super.rollback();
        } finally {
            super.closeConn();
        }
        return 0;
    }
}
