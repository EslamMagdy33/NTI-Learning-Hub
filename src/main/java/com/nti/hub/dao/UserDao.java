package com.nti.hub.dao;

import  com.nti.hub.model.User;

public interface UserDao {
    boolean save(User user);
    User findByEmail(String email);
    int count();
    boolean deleteByEmail(String email);

}
