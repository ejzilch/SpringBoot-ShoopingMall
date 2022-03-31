package com.ejzilch.shoppingmall.product.service;

import com.ejzilch.shoppingmall.product.entity.Product;
import com.ejzilch.shoppingmall.product.entity.ProductRequest;

public interface ProductService {

    Product findProductById(Integer productId);

    ProductRequest createProduct(ProductRequest productRequest);
}
