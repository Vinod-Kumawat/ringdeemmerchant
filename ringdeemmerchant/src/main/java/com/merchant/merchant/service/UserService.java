package com.merchant.merchant.service;

import com.merchant.merchant.bean.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUser();
    public User getUserByID(String id);
    public User saveUSer(User user);
    public User getUserByContactName(String contactName);
    }
