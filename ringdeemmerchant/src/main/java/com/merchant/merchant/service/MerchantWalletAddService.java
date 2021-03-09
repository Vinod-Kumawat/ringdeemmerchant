package com.merchant.merchant.service;

import com.merchant.merchant.bean.MerchantWalletAdd;

import java.util.List;

public interface MerchantWalletAddService {
    public MerchantWalletAdd getWalletByID(Integer id);
    public List<MerchantWalletAdd> getAllWallet();
    public List<MerchantWalletAdd> getWalletByMerchantId(Integer mechantID);
    public MerchantWalletAdd saveWallet(MerchantWalletAdd merchantWalletAdd);
}
