package com.merchant.merchant.dao;


import com.merchant.merchant.bean.User;
import com.merchant.merchant.bean.UserPointHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserPointHistoryRepository extends JpaRepository<UserPointHistory, Integer> {
        @Transactional
        List<UserPointHistory> findAll();
        @Transactional
        UserPointHistory save(UserPointHistory userPointHistory);
        @Transactional
        UserPointHistory findByUserId(String userId);
        @Transactional
        List<UserPointHistory> findByProductId(String productID);
        @Transactional
        List<UserPointHistory> findByMechantID(Integer merchantId);
    }

