package com.merchant.merchant.dao;

import com.merchant.merchant.bean.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    public List<Category> findByMerchantId(Integer merchantID);
    public List<Category> findAll();
    public Category findByCategoryId(Integer categoryId);
    public Category save(Category category);

}
