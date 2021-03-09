package com.merchant.merchant.util;

import com.merchant.merchant.bean.Product;
import com.merchant.merchant.dto.ProductPOJO;

public class ProductToPOJOConverter {

    public static ProductPOJO convertProductToPOJO(Product product,ProductPOJO productPOJO)
    {
        productPOJO.setProductId(product.getProductId());
        productPOJO.setProductPoint(product.getProductPoint());
        productPOJO.setProductName(product.getProductName());
        productPOJO.setStatus(product.getStatus());
        productPOJO.setDescription(product.getDescription());
        productPOJO.setOtherInfo(product.getOtherInfo());
        productPOJO.setMechantID(product.getMechantID());
        //productPOJO.setShowOnDay(product.getShowOnDay());
        productPOJO.setCategory(product.getCategory());
        productPOJO.setStartdate(product.getStartdate());
        productPOJO.setEnddate(product.getEnddate());
        productPOJO.setPrice(product.getPrice());
        productPOJO.setDiscountprice(product.getDiscountprice());
        return productPOJO;
    }

    public static Product convertPojoToProduct(ProductPOJO productForm,Product product,String filename){
        if(null!=productForm.getProductId()){
            product.setProductId(productForm.getProductId());
        }
        if(null!=filename && filename.length()>3) {
            product.setImage(filename);
        }
        product.setProductName(productForm.getProductName());
        product.setProductPoint(productForm.getProductPoint());
        product.setMechantID(productForm.getMechantID());
        product.setDescription(productForm.getDescription());
        //product.setShowOnDay(productForm.getShowOnDay());
        product.setOtherInfo(productForm.getOtherInfo());
        product.setStatus(productForm.getStatus());
        product.setCategory(productForm.getCategory());
        product.setStartdate(productForm.getStartdate());
        product.setEnddate(productForm.getEnddate());
        product.setPrice(productForm.getPrice());
        product.setDiscountprice(productForm.getDiscountprice());
        return product;
    }
}
