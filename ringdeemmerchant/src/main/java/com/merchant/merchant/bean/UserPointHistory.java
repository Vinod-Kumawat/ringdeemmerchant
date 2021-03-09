package com.merchant.merchant.bean;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name="history")
@DynamicUpdate
public class UserPointHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer historyId;
    @Column(name="user_id")
    private String userId;

    @Column(name="product_id")
    private String productId;
    @Column(name="product_point")
    private String productPoint;
    @Column(name="amount")
    private String amount;
    @Column(name="discountprice")
    private String discountprice;
    @Column(name="purchaseDate")
    private Timestamp datetime;
    @Column
    private Integer mechantID;

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

    public Integer getMechantID() {
        return mechantID;
    }

    public void setMechantID(Integer mechantID) {
        this.mechantID = mechantID;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDiscountprice() {
        return discountprice;
    }

    public void setDiscountprice(String discountprice) {
        this.discountprice = discountprice;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "UserPointHistory{" +
                "historyId=" + historyId +
                ", userId='" + userId + '\'' +
                ", productId='" + productId + '\'' +
                ", productPoint='" + productPoint + '\'' +
                ", amount='" + amount + '\'' +
                ", discountprice='" + discountprice + '\'' +
                ", datetime=" + datetime +
                ", mechantID=" + mechantID +
                '}';
    }
}
