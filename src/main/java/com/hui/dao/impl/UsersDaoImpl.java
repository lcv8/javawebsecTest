package com.hui.dao.impl;

import com.hui.dao.BaseDao;
import com.hui.dao.UserDao;
import com.hui.entity.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsersDaoImpl extends BaseDao implements UserDao {
    @Override
    public List<Users> queryUser(Users users) {
        List<Users> list = new ArrayList<>();
        try {
            Connection conn = super.getConn();
            StringBuffer sql = new StringBuffer("SELECT * FROM `users` WHERE 1 = 1 ");
            if(users != null){
                if(users.getUserCode() != null){
                    sql.append(" AND user_code = ? ");
                }
                if(users.getUserId() != null){
                    sql.append(" AND user_id = ? ");
                }
                if(users.getPassword() != null){
                    sql.append(" AND password = MD5(?) ");
                }
            }
            PreparedStatement statement = conn.prepareStatement(sql.toString());
            int index = 0;
            if(users != null){
                if(users.getUserCode() != null){
                   statement.setObject(++index,users.getUserCode());
                }
                if(users.getUserId() != null){
                    statement.setObject(++index,users.getUserId());
                }
                if(users.getPassword() != null){
                    statement.setObject(++index,users.getPassword());
                }
            }
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Users userTemp = new Users();
                userTemp.setUserId(resultSet.getInt("user_id"));
                userTemp.setUserCode(resultSet.getString("user_code"));
                userTemp.setEmail(resultSet.getString("email"));
                userTemp.setGender(resultSet.getInt("gender"));
                list.add(userTemp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            super.closeConn();
        }
        return list;
    }

    @Override
    public int insertUser(Users users) {
        try {
            Connection conn = super.getConn(false);
            StringBuffer sql = new StringBuffer();
            sql.append(" INSERT INTO `users`(`password`,`user_code`,`email`,`gender`) ");
            sql.append(" VALUES (MD5(?),?,?,?) ");
            PreparedStatement statement = conn.prepareStatement(sql.toString());
            statement.setObject(1,users.getPassword());
            statement.setObject(2,users.getUserCode());
            statement.setObject(3,users.getEmail());
            statement.setObject(4,users.getGender());
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
    public int updateUser(Users users) {
        try {
            Connection conn = super.getConn();
            StringBuffer sql = new StringBuffer(" UPDATE `users` SET ");
            if(users != null){
                if(users.getGender() != null){
                    sql.append(" gender = ? , ");
                }
                if(users.getEmail() != null){
                    sql.append(" email = ? , ");
                }
                if(users.getNewPassword() != null){
                    sql.append(" password = MD5(?) , ");
                }
                if(users.getLastLoginTime() != null){
                    sql.append(" last_logintime = ? , ");
                }
            }
            sql.append(" update_time = ? ");
            sql.append(" where 1 = 1 ");
            if(users != null){
                if(users.getUserId() != null){
                    sql.append(" AND user_id = ? ");
                }
                if(users.getPassword() != null){
                    sql.append(" AND password = MD5(?) ");
                }
                if(users.getUserCode() != null){
                    sql.append(" AND user_code = ? ");
                }
            }
            PreparedStatement statement = conn.prepareStatement(sql.toString());
            int index = 0;
            if(users != null){
                if(users.getGender() != null){
                    statement.setObject(++index,users.getGender());
                }
                if(users.getEmail() != null){
                    statement.setObject(++index,users.getEmail());
                }
                if(users.getNewPassword() != null){
                    statement.setObject(++index,users.getNewPassword());
                }
                if(users.getLastLoginTime() != null){
                    statement.setObject(++index,users.getLastLoginTime());
                }
            }
            statement.setObject(++index,new Date());
            if(users != null){
                if(users.getUserId() != null){
                    statement.setObject(++index,users.getUserId());
                }
                if(users.getPassword() != null){
                    statement.setObject(++index,users.getPassword());
                }
                if(users.getUserCode() != null){
                    statement.setObject(++index,users.getUserCode());
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
