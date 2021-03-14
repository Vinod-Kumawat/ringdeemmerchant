package com.merchant.merchant.dao;

import com.merchant.merchant.bean.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
        @Transactional
        List<Product> findAll();
        @Transactional
        Product save(Product product);
        @Transactional
        Product findByProductId(Integer id);
        @Transactional
        List<Product> findByMechantID(Integer merchantId);
        @Transactional
        List<Product> findByStatus(String status);
        @Transactional
        void delete(Product product);
        @Transactional
        List<Product> findByProductIdIn(Set<Integer> integerSet);

        @Transactional
        @Modifying
        @Query(value = "UPDATE product p SET p.product_point=?1, p.status=?2 WHERE p.product_id=?3",
                nativeQuery = true)
        void updateProductByAdmin(int productPoint, String status, Integer productId);


    }

