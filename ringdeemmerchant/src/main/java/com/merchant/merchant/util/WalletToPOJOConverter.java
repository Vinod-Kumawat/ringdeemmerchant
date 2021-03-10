package com.merchant.merchant.util;


import com.merchant.merchant.bean.MerchantWalletAdd;

import com.merchant.merchant.dto.MerchantWalletAddPOJO;

import java.sql.Date;
import java.sql.Timestamp;

public class WalletToPOJOConverter {

    public static MerchantWalletAddPOJO convertMerchantWalletToPOJO(MerchantWalletAdd mva, MerchantWalletAddPOJO mvap)
    {
        mvap.setWalletID(mva.getWalletID());
        mvap.setMerchantId(mva.getMerchantId());
        mvap.setAmount(mva.getAmount());
        mvap.setTopupDateTime(mva.getTopupDateTime());
        return mvap;
    }

    public static MerchantWalletAdd convertPojoToMerchantWallet(MerchantWalletAddPOJO mvap, MerchantWalletAdd mva, String filename){
        if(null!=mvap.getWalletID()){
            mva.setWalletID(mvap.getWalletID());
            mva.setTopupDateTime(mvap.getTopupDateTime());
        }
        mva.setAmount(mvap.getAmount());
        mva.setMerchantId(mvap.getMerchantId());
        mva.setImage(filename);
        mva.setTopupDateTime(new Timestamp(System.currentTimeMillis()));
        return mva;
    }
}
