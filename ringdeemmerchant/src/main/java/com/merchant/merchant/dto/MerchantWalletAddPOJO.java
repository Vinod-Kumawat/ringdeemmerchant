package com.merchant.merchant.dto;



import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;


public class MerchantWalletAddPOJO {


    private Integer walletID;
    private Integer merchantId;
    private Integer amount;
    private MultipartFile image;
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "MerchantWalletAdd{" +
                "walletID=" + walletID +
                ", topupDateTime=" + topupDateTime +
                ", merchantId=" + merchantId +
                ", amount=" + amount +
                ", image='" + image.getOriginalFilename() + '\'' +
                '}';
    }
}
