package com.merchant.merchant.dao;

import com.merchant.merchant.bean.MerchantQuery;
import com.merchant.merchant.bean.MerchantWalletAdd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MerchantQueryRepository extends JpaRepository<MerchantQuery, Integer> {

    @Transactional
    MerchantQuery save(MerchantQuery merchantQuery);
    @Transactional
    List<MerchantQuery> findAll();
    @Transactional
    MerchantQuery findByMerchantQueryId(Integer merchantQueryId);
    @Transactional
    List<MerchantQuery> findByMechantID(Integer merchantId);

}
