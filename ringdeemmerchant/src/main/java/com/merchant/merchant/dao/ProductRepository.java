package com.merchant.merchant.dao;

import com.merchant.merchant.bean.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
        List<Product> findAll();
        Product save(Product product);
        Product findByProductId(Integer id);
        List<Product> findByMechantID(Integer merchantId);
        void delete(Product product);

        @Modifying
        @Query(value = "UPDATE Product p set image =:image where p.productId = :productId",
                nativeQuery = true)
        void updateProduct(@Param("image") String image, @Param("productId") Integer productId);
    }

