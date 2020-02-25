package com.sx.dao;

import com.sx.pojo.User;

import java.util.List;

public interface IUserDao {
    List<User> findAll();
    User findById(User u);
    void addUser(User u);
    void deleteUserById(User u);
    void updateUser(User u);
}
