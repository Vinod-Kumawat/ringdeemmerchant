package com.merchant.merchant.bean;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="user")
@DynamicUpdate
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="userid")
    private String userId;
    @Column(name="point")
    private String point;
    @Column(name="address")
    private String address;
    @Column(name="country")
    private String country;
    @Column(name="contactname")
    private String contactName;
    @Column
    private Date createddate;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", point='" + point + '\'' +
                ", address='" + address + '\'' +
                ", country='" + country + '\'' +
                ", contactName='" + contactName + '\'' +
                ", createddate=" + createddate +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }
}
