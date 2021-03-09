package com.merchant.merchant.bean;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name="merchant")
@DynamicUpdate
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer merchantId;
    @Column
    private String companyName;
    @Column
    private  String merchantMail;
    @Column
    private String password;
    @Column
    private String contactName;
    @Column
    private String countryCode;
    @Column
    private String mobileNumber;
    @Column
    private String description;
    @Column
    private String image;
    @Column
    private String businessAddress;
    @Column
    private String businessNumber;
    @Column
    private String taxNumber;
    @Column
    private String vatTax;
    @Column
    private String country;
    // point will consider amount/payment
    @Column
    private long point;
    @Column
    private String currency;
    @Column
    private Date createddate;



    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getBusinessNumber() {
        return businessNumber;
    }

    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getVatTax() {
        return vatTax;
    }

    public void setVatTax(String vatTax) {
        this.vatTax = vatTax;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public String getMerchantMail() {
        return merchantMail;
    }

    public void setMerchantMail(String merchantMail) {
        this.merchantMail = merchantMail;
    }

    public long getPoint() {
        return point;
    }

    public void setPoint(long point) {
        this.point = point;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    @Override
    public String toString() {
        return "Merchant{" +
                "merchantId=" + merchantId +
                ", companyName='" + companyName + '\'' +
                ", merchantMail='" + merchantMail + '\'' +
                ", password='" + password + '\'' +
                ", contactName='" + contactName + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", businessAddress='" + businessAddress + '\'' +
                ", businessNumber='" + businessNumber + '\'' +
                ", taxNumber='" + taxNumber + '\'' +
                ", vatTax='" + vatTax + '\'' +
                ", country='" + country + '\'' +
                ", point=" + point +
                ", currency='" + currency + '\'' +
                ", createddate='" + createddate + '\'' +
                '}';
    }
}
