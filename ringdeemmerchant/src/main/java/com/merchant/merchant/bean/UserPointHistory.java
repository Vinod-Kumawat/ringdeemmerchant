package com.merchant.merchant.bean;

import javax.persistence.*;

@Entity
@Table(name="history")
public class UserPointHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer historyId;
    @Column(name="user_id")
    private String userId;

    @Column(name="product_id")
    private String productId;
    @Column(name="product_point")
    private String productPoint;

    @Override
    public String toString() {
        return "UserPointHistory{" +
                "historyId=" + historyId +
                ", userId='" + userId + '\'' +
                ", productId='" + productId + '\'' +
                ", productPoint='" + productPoint + '\'' +
                '}';
    }

    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductPoint() {
        return productPoint;
    }

    public void setProductPoint(String productPoint) {
        this.productPoint = productPoint;
    }
}
