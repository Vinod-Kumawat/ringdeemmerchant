package com.merchant.merchant.bean;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="MerchantQuery")
public class MerchantQuery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer merchantQueryId;
    @Column(name="mechantID")
    private Integer mechantID;
    @Column(name="merchantName")
    private String merchantName;
    @Column(name="msgquery")
    private String msgquery;
    @Column(name="msgqueryDate")
    private Date date;
    @Column(name="status")
    private String status="pending";

    public Integer getMerchantQueryId() {
        return merchantQueryId;
    }

    public void setMerchantQueryId(Integer merchantQueryId) {
        merchantQueryId = merchantQueryId;
    }

    public Integer getMechantID() {
        return mechantID;
    }

    public void setMechantID(Integer mechantID) {
        this.mechantID = mechantID;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMsgquery() {
        return msgquery;
    }

    public void setMsgquery(String msgquery) {
        this.msgquery = msgquery;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MerchantQuery{" +
                "MerchantQueryId=" + merchantQueryId +
                ", mechantID=" + mechantID +
                ", merchantName='" + merchantName + '\'' +
                ", msgquery='" + msgquery + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                '}';
    }
}
