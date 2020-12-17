package com.hui.entity;

import java.util.Date;

/**
 * @author lcv8
 */
public class Users {
    private Integer userId;
    private String userCode;
    private String password;
    private String newPassword;
    private String email;
    private Integer gender;
    private Date registerTime;
    private Date lastLoginTime;
    private Date updateTime;

    public Integer getUserId() {
        return userId;
    }

    public Users setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getUserCode() {
        return userCode;
    }

    public Users setUserCode(String userCode) {
        this.userCode = userCode;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Users setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public Users setNewPassword(String newPassword) {
        this.newPassword = newPassword;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Users setEmail(String email) {
        this.email = email;
        return this;
    }

    public Integer getGender() {
        return gender;
    }

    public Users setGender(Integer gender) {
        this.gender = gender;
        return this;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public Users setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
        return this;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public Users setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Users setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", userCode='" + userCode + '\'' +
                ", password='" + password + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", registerTime=" + registerTime +
                ", lastLoginTime=" + lastLoginTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
