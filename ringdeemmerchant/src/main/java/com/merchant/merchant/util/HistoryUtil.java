package com.merchant.merchant.util;

import com.merchant.merchant.bean.Product;
import com.merchant.merchant.bean.User;
import com.merchant.merchant.bean.UserPointHistory;

import java.sql.Timestamp;

/**
 * this class create transaction hoistory objet for each user, product and merchant against Purchase
 * Author : Vinod
 */
public class HistoryUtil {
    public static UserPointHistory createHistoryOnPurchase(User user1, Product product)
    {
        // create history object
        UserPointHistory userPointHistory=new UserPointHistory();
        userPointHistory.setUserId(user1.getUserId());
        userPointHistory.setProductPoint(String.valueOf(product.getProductPoint()));
        userPointHistory.setProductId(String.valueOf(product.getProductId()));
        userPointHistory.setDatetime(new Timestamp(System.currentTimeMillis()));
        userPointHistory.setMechantID(product.getMechantID());
        userPointHistory.setAmount(product.getPrice().toString());
        userPointHistory.setDiscountprice(product.getDiscountprice().toString());
        userPointHistory.setCountry(user1.getCountry());
        userPointHistory.setStatus("success");
        return userPointHistory;
    }
}
