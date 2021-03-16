package com.merchant.merchant.service;

import com.merchant.merchant.bean.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> findAllCategory();
    public List<Category> findCategoryByMerchantId(Integer merchantId);
    public Category saveCategory(Category category);
    public Category findByCategoryId(Integer id);
}
