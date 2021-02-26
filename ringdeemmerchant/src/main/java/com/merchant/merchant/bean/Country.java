package com.merchant.merchant.bean;

import javax.persistence.*;

@Entity
@Table(name="country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String iso;
    @Column
    private String name;
    @Column(name="nicename")
    private String niceName;
    @Column
    private String iso3;
    @Column(name="numcode")
    private Integer numCode;
    @Column(name="phonecode")
    private Integer phoneCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNiceName() {
        return niceName;
    }

    public void setNiceName(String niceName) {
        this.niceName = niceName;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    public Integer getNumCode() {
        return numCode;
    }

    public void setNumCode(Integer numCode) {
        this.numCode = numCode;
    }

    public Integer getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(Integer phoneCode) {
        this.phoneCode = phoneCode;
    }

    @Override
    public String  toString() {
        return "Country{" +
                "id=" + id +
                ", iso='" + iso + '\'' +
                ", name='" + name + '\'' +
                ", niceName='" + niceName + '\'' +
                ", iso3='" + iso3 + '\'' +
                ", numCode=" + numCode +
                ", phoneCode=" + phoneCode +
                '}';
    }
}
