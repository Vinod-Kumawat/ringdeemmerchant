package com.merchant.merchant.dao;

import com.merchant.merchant.bean.MerchantQuery;
import com.merchant.merchant.bean.MerchantWalletAdd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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
    @Transactional
    List<MerchantQuery> findByStatus(String status);

    public static final String TOTAL_COUNT="SELECT count(mq.merchant_query_id) AS total FROM merchant_query mq WHERE mq.status='pending'";
    @Transactional
    @Query(value =TOTAL_COUNT ,nativeQuery = true)
    public long countTotalQuery();

    public static final String RESOLVED_STATUS="UPDATE merchant_query mq SET mq.status='resolved' WHERE mq.merchant_query_id=?1";
    @Transactional
    @Modifying
    @Query(value =RESOLVED_STATUS ,nativeQuery = true)
    public void changeStatus(Integer merchantQueryID);

}
