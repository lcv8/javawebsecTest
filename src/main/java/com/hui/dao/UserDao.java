package com.hui.dao;

import com.hui.entity.Users;

import java.util.List;

/**
 * @author lcv8
 */
public interface UserDao {
    List<Users> queryUser(Users users);
    int insertUser(Users users);
    int updateUser(Users users);
}
