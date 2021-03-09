package com.merchant.merchant.bean;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="MerchantPointHistory")
public class MerchantPointHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer merchantHistoryId;
    @Column
    private Integer mechantID;
    @Column(name="added_point")
    private String addedPoint;
    @Column(name="pointUpdateDate")
    private Date date;

    @Override
    public String toString() {
        return "MerchantPointHistory{" +
                "merchantHistoryId=" + merchantHistoryId +
                ", mechantID=" + mechantID +
                ", addedPoint='" + addedPoint + '\'' +
                ", date=" + date +
                '}';
    }

    public Integer getMerchantHistoryId() {
        return merchantHistoryId;
    }

    public void setMerchantHistoryId(Integer merchantHistoryId) {
        this.merchantHistoryId = merchantHistoryId;
    }

    public Integer getMechantID() {
        return mechantID;
    }

    public void setMechantID(Integer mechantID) {
        this.mechantID = mechantID;
    }

    public String getAddedPoint() {
        return addedPoint;
    }

    public void setAddedPoint(String addedPoint) {
        this.addedPoint = addedPoint;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
