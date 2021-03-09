package com.merchant.merchant.dao;

import com.merchant.merchant.bean.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
/*
        @Modifying
        @Query(value = "UPDATE Product p set image =:image where p.productId = :productId",
                nativeQuery = true)
        void updateProduct(@Param("image") String image, @Param("productId") Integer productId);

 */
    }

