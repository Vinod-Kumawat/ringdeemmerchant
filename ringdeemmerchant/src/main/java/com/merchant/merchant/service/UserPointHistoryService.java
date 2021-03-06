package com.merchant.merchant.service;

import com.merchant.merchant.bean.UserPointHistory;

import java.util.List;

public interface UserPointHistoryService {
    public List<UserPointHistory> findAll();
    public UserPointHistory saveUserPointHistory(UserPointHistory userPointHistory);
    public UserPointHistory findByUserId(String userId);
    public UserPointHistory findByProductId(String productID);
}
