package com.merchant.merchant.dao;

import com.merchant.merchant.bean.MerchantWalletAdd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MerchantWalletAddRepository extends JpaRepository<MerchantWalletAdd, Integer> {

    @Transactional
    MerchantWalletAdd save(MerchantWalletAdd merchantWalletAdd);
    @Transactional
    List<MerchantWalletAdd> findAll();
    @Transactional
    List<MerchantWalletAdd> findByMerchantId(Integer merchantID);
    @Transactional
    MerchantWalletAdd findByWalletID(Integer walletId);

}