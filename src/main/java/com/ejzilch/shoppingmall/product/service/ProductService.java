package com.ejzilch.shoppingmall.product.service;

import com.ejzilch.shoppingmall.product.entity.Product;

public interface ProductService {

    Product findProductById(Integer productId);

    Product createProduct(Product product);

    Product updateProduct(Integer productId, Product product);

    void deleteProduct(Integer productId);
}
