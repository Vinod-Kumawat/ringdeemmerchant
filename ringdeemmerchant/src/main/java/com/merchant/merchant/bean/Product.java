package com.merchant.merchant.bean;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Date;
import java.util.Map;

@Entity
@Table(name="product")
@DynamicUpdate
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @Column
    private Integer mechantID;
    @Column
    private String productName;
    @Column
    private int productPoint;
    @Column
    private String image;
    @Column
    private String showOnDay;
    @Column
    private  String description;
    @Column
    private String otherInfo;
    @Column
    private String status;
    @Column
    private String category;
    @Column
    private Date startdate;
    @Column
    private Date enddate;
    @Column
    private Integer price;
    @Column
    private Integer discountprice;


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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDiscountprice() {
        return discountprice;
    }

    public void setDiscountprice(Integer discountprice) {
        this.discountprice = discountprice;
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
                ", category='" + category + '\'' +
                ", startdate=" + startdate +
                ", enddate=" + enddate +
                ", price=" + price +
                ", discountprice=" + discountprice +
                '}';
    }
}
