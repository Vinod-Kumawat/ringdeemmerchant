package com.merchant.merchant.dao;


import com.merchant.merchant.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Transactional
    List<User> findAll();
    @Transactional
    User save(User user);
    @Transactional
    User findByUserId(String userId);
    @Transactional
    User findByContactName(String contactName);
}

