package com.merchant.merchant.service.impl;

import com.merchant.merchant.bean.UserPointHistory;
import com.merchant.merchant.dao.UserPointHistoryRepository;
import com.merchant.merchant.service.UserPointHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserPointHistoryServiceImpl implements UserPointHistoryService {

    @Autowired
    UserPointHistoryRepository userPointHistoryRepository;

    @Override
    public List<UserPointHistory> findAll() {
        List<UserPointHistory> userPointHistoryList=userPointHistoryRepository.findAll();
        return userPointHistoryList;
    }

    @Override
    public UserPointHistory saveUserPointHistory(UserPointHistory userPointHistory) {
        return userPointHistoryRepository.save(userPointHistory);
    }

    @Override
    public List<UserPointHistory> findByUserId(String userId) {
        return userPointHistoryRepository.findByUserId(userId);
    }

    @Override
    public List<UserPointHistory> findByProductId(String productID) {
        return userPointHistoryRepository.findByProductId(productID);
    }

    @Override
    public List<UserPointHistory> findByMerchantID(Integer merchantId) {
        System.out.println(merchantId);
        return userPointHistoryRepository.findByMechantID(merchantId);
    }
}
