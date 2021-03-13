package com.merchant.merchant.dao;

import com.merchant.merchant.bean.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Integer> {

    @Transactional
    Merchant save(Merchant merchant);
    @Transactional
    List<Merchant> findAll();
    @Transactional
    Merchant findByMerchantId(Integer id);
    @Transactional
    Merchant findByMerchantMail(String merchantMail);

    public static final String FIND_POINT = "SELECT m.point FROM merchant m WHERE m.merchant_id=?1";
    @Transactional
    @Query(value = FIND_POINT, nativeQuery = true)
    public long findMerchantPoint(Integer id);

    public static final String UPDATE_POINT = "UPDATE merchant m set m.point=?1 WHERE m.merchant_id=?2";
    @Transactional
    @Modifying
    @Query(value = UPDATE_POINT, nativeQuery = true)
    public void updateMerchantPoint(Long point,Integer id);

    public static final String LESS_POINT_MERCHANT = "SELECT m.* FROM merchant m WHERE m.point<500";
    @Transactional
    @Query(value = LESS_POINT_MERCHANT, nativeQuery = true)
    public List<Merchant> findMerchantByLowPoint();
  /*  Integer update(Merchant merchant);*/
    /*
    Merchant save(Merchant merchant);

    Integer update(Merchant merchant);
    void delete(Merchant merchant);
*/
}
