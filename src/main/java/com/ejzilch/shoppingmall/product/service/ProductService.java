package com.ejzilch.shoppingmall.product.service;

import com.ejzilch.shoppingmall.product.dto.ProductQueryParams;
import com.ejzilch.shoppingmall.product.entity.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Product findProductById(Integer productId);

    List<Product> findProducts(ProductQueryParams productQueryParams, Pageable pageable);

    Product createProduct(Product product);

    Product updateProduct(Integer productId, Product product);

    void deleteProduct(Integer productId);
}
