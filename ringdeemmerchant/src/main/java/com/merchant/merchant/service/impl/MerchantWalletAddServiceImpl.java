package com.merchant.merchant.service.impl;

import com.merchant.merchant.bean.MerchantWalletAdd;
import com.merchant.merchant.dao.MerchantWalletAddRepository;
import com.merchant.merchant.service.MerchantWalletAddService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MerchantWalletAddServiceImpl implements MerchantWalletAddService {

    @Autowired
    MerchantWalletAddRepository merchantWalletAddRepository;

    @Override
    public MerchantWalletAdd getWalletByID(Integer id) {
        return merchantWalletAddRepository.findByWalletID(id);
    }

    @Override
    public List<MerchantWalletAdd> getAllWallet() {
        return merchantWalletAddRepository.findAll();
    }
 
    @Override
    public List<MerchantWalletAdd> getWalletByMerchantId(Integer mechantID) {
        return merchantWalletAddRepository.findByMerchantId(mechantID);
    }

    @Override
    public MerchantWalletAdd saveWallet(MerchantWalletAdd merchantWalletAdd) {
        return merchantWalletAddRepository.save(merchantWalletAdd);
    }
}
