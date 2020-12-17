package com.hui.service.impl;

import com.hui.dao.UserDao;
import com.hui.dao.impl.UsersDaoImpl;
import com.hui.entity.Users;
import com.hui.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UsersDaoImpl();
    @Override
    public List<Users> queryUser(Users users) {
        return userDao.queryUser(users);
    }

    @Override
    public int insertUser(Users users) {

        return userDao.insertUser(users);
    }

    @Override
    public int updateUser(Users users) {
        return userDao.updateUser(users);
    }

    @Override
    public String checkUsers(Users users) {
        if(users.getUserCode() == null || users.getUserCode().trim().equals("")
                || users.getPassword() == null || users.getPassword().trim().equals("")){
            return "101";
        }
        if(users.getGender() == null || users.getGender() == -1){
            return "104";
        }
        List<Users> list = this.queryUser(new Users().setUserCode(users.getUserCode()));
        if(list.size() != 0){
            return "102";
        }
        return "100";
    }
}
