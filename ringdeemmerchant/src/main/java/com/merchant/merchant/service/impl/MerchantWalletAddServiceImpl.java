package com.merchant.merchant.service.impl;

import com.merchant.merchant.bean.MerchantWalletAdd;
import com.merchant.merchant.dao.MerchantWalletAddRepository;
import com.merchant.merchant.service.MerchantWalletAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    @Override
    public void approveWalletByAdmin(Integer walletId) {
        merchantWalletAddRepository.apporveWalletBalance(walletId);
    }

    @Override
    public List<MerchantWalletAdd> findByStatus(String status) {
        return merchantWalletAddRepository.findByStatus(status);
    }
}
