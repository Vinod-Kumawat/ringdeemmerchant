package com.merchant.merchant.dao;


import com.merchant.merchant.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
        List<User> findAll();
        User save(User user);
        User findByUserId(String userId);
        User findByContactName(String contactName);
    }

