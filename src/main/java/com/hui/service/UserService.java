package com.hui.service;

import com.hui.entity.Users;

import java.util.List;

public interface UserService {
    List<Users> queryUser(Users users);
    int insertUser(Users users);
    int updateUser(Users users);
    String checkUsers(Users users);
}
