package com.merchant.merchant.dao;


import com.merchant.merchant.bean.User;
import com.merchant.merchant.bean.UserPointHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPointHistoryRepository extends JpaRepository<UserPointHistory, Integer> {
        List<UserPointHistory> findAll();
        UserPointHistory save(UserPointHistory userPointHistory);
        UserPointHistory findByUserId(String userId);
        UserPointHistory findByProductId(String productID);
    }

