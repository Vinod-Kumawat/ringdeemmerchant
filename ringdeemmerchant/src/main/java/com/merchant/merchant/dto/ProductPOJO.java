package com.merchant.merchant.dto;

import org.springframework.web.multipart.MultipartFile;

public class ProductPOJO {

    private Integer productId;

    private Integer mechantID;
    private String productName;
    private int productPoint;
    private MultipartFile image;
    private String showOnDay;
    private  String description;
    private String otherInfo;
    private String status;


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getMechantID() {
        return mechantID;
    }

    public void setMechantID(Integer mechantID) {
        this.mechantID = mechantID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPoint() {
        return productPoint;
    }

    public void setProductPoint(int productPoint) {
        this.productPoint = productPoint;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getShowOnDay() {
        return showOnDay;
    }

    public void setShowOnDay(String showOnDay) {
        this.showOnDay = showOnDay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", mechantID=" + mechantID +
                ", productName='" + productName + '\'' +
                ", productPoint=" + productPoint +
                ", image='" + image + '\'' +
                ", showOnDay='" + showOnDay + '\'' +
                ", description='" + description + '\'' +
                ", otherInfo='" + otherInfo + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
