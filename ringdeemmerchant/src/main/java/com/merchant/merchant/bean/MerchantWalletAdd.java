package com.merchant.merchant.bean;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;

@Entity
@Table(name="merchant_wallet")
@DynamicUpdate
public class MerchantWalletAdd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer walletID;
    @Column(name = "merchant_id")
    private Integer merchantId;
    @Column(name="amount")
    private Integer amount;
    @Column(name = "image")
    private String image;
    @Column(name="topup_date_time")
    private Timestamp topupDateTime;

    public Integer getWalletID() {
        return walletID;
    }

    public void setWalletID(Integer walletID) {
        this.walletID = walletID;
    }

    public Timestamp getTopupDateTime() {
        return topupDateTime;
    }

    public void setTopupDateTime(Timestamp topupDateTime) {
        this.topupDateTime = topupDateTime;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "MerchantWalletAdd{" +
                "walletID=" + walletID +
                ", topupDateTime=" + topupDateTime +
                ", merchantId=" + merchantId +
                ", amount=" + amount +
                ", image='" + image + '\'' +
                '}';
    }
}
