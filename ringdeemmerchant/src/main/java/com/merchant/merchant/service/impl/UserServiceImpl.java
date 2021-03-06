package com.merchant.merchant.service.impl;

import com.merchant.merchant.bean.User;
import com.merchant.merchant.dao.UserRepository;
import com.merchant.merchant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        List<User> userList=userRepository.findAll();
        return userList;
    }

    @Override
    public User getUserByID(String id) {
        User user= userRepository.findByUserId(id);
        return user;
    }

    @Override
    public User saveUSer(User user) {
        User user1=userRepository.save(user);
        return user1;
    }
}
