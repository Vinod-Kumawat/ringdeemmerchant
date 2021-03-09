package com.merchant.merchant.util;

import com.merchant.merchant.bean.Merchant;
import com.merchant.merchant.dto.MerchantPOJO;

import java.sql.Date;

public class MerchantToPOJOConverter {

    public static MerchantPOJO convertMerchantToPOJO(Merchant merchant,MerchantPOJO merchantPOJO)
    {
        merchantPOJO.setMerchantId(merchant.getMerchantId());
        merchantPOJO.setMerchantMail(merchant.getMerchantMail());
        merchantPOJO.setDescription(merchant.getDescription());
        merchantPOJO.setBusinessAddress(merchant.getBusinessAddress());
        merchantPOJO.setCompanyName(merchant.getCompanyName());
        merchantPOJO.setCountry(merchant.getCountry());
        merchantPOJO.setCountryCode(merchant.getCountryCode());
        merchantPOJO.setBusinessNumber(merchant.getBusinessNumber());
        merchantPOJO.setMobileNumber(merchant.getMobileNumber());
        merchantPOJO.setContactName(merchant.getContactName());
        merchantPOJO.setVatTax(merchant.getVatTax());
        merchantPOJO.setTaxNumber(merchant.getTaxNumber());
        merchantPOJO.setPoint(merchant.getPoint());
        merchantPOJO.setPassword(merchant.getPassword());
        merchantPOJO.setCurrency(merchant.getCurrency());
        merchantPOJO.setCreatedate(merchant.getCreateddate());
        return merchantPOJO;
    }

    public static Merchant convertPojoToMerchant(MerchantPOJO merchantForm, Merchant merchant, String filename){
        if(null!=merchantForm.getMerchantId()){
            merchant.setMerchantId(merchantForm.getMerchantId());
            merchant.setCreateddate(merchantForm.getCreatedate());
        }
        else
        {
            merchant.setCreateddate(new Date(System.currentTimeMillis()));
        }
        merchant.setCompanyName(merchantForm.getCompanyName());
        merchant.setMerchantMail(merchantForm.getMerchantMail());
        merchant.setImage(filename);
        merchant.setDescription(merchantForm.getDescription());
        merchant.setBusinessAddress(merchantForm.getBusinessAddress());
        merchant.setBusinessNumber(merchantForm.getBusinessNumber());
        merchant.setContactName(merchantForm.getContactName());
        merchant.setCountry(merchantForm.getCountry());
        merchant.setCountryCode(merchantForm.getCountryCode());
        merchant.setMobileNumber(merchantForm.getMobileNumber());
        merchant.setPassword(merchantForm.getPassword());
        merchant.setPoint(merchantForm.getPoint());
        merchant.setTaxNumber(merchantForm.getTaxNumber());
        merchant.setVatTax(merchantForm.getVatTax());
        merchant.setCurrency(merchantForm.getCurrency());
        return merchant;
    }
}
