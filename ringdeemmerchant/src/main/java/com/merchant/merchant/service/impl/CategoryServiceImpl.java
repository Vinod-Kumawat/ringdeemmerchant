package com.merchant.merchant.service.impl;

import com.merchant.merchant.bean.Category;
import com.merchant.merchant.dao.CategoryRepository;
import com.merchant.merchant.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findCategoryByMerchantId(Integer merchantId) {
        return categoryRepository.findByMerchantId(merchantId);
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findByCategoryId(Integer id) {
        return categoryRepository.findByCategoryId(id);
    }
}
