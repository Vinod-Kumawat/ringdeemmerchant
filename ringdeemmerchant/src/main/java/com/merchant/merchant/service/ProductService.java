package com.merchant.merchant.service;

import com.merchant.merchant.bean.Product;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Set;

public interface ProductService {
    public Product addProduct(Product product);
    public Product updateProduct(Product product);
    public int deleteProduct(int id);
    public List<Product> viewProduct();
    public Product viewProductByID(Integer id);
    public List<Product> viewProductByMerchantID(Integer merchantID);
    public List<Product> viewProductByStatus(String status);
    public void deleteProductByID(Integer id);
    public void updateProductByAdmin(int point, String status, Integer id);
    public List<Product> viewProductByIDIn(Set<Integer> integerSet);

 //   public List<Product> viewProductByCountry(String country);
  //  public Product getProductByEmail(String merchantMail);
}
