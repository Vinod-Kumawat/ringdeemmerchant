package com.merchant.merchant.dto;


import org.springframework.web.multipart.MultipartFile;

public class MerchantPOJO {
    private Integer merchantId;
    private String companyName;
    private  String merchantMail;
    private String password;
    private String contactName;
    private String countryCode;
    private String mobileNumber;
    private String description;
    private MultipartFile image;
    private String businessAddress;
    private String businessNumber;
    private String taxNumber;
    private String vatTax;
    private String country;
    private long point;

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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
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
                ", businessAddress='" + businessAddress + '\'' +
                ", businessNumber='" + businessNumber + '\'' +
                ", taxNumber='" + taxNumber + '\'' +
                ", vatTax='" + vatTax + '\'' +
                ", country='" + country + '\'' +
                ", point=" + point +
                '}';
    }
}
