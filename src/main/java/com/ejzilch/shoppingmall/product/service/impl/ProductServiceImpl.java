package com.ejzilch.shoppingmall.product.service.impl;

import com.ejzilch.shoppingmall.product.dao.ProductRepository;
import com.ejzilch.shoppingmall.product.dao.ProductRequestRepository;
import com.ejzilch.shoppingmall.product.entity.Product;
import com.ejzilch.shoppingmall.product.entity.ProductRequest;
import com.ejzilch.shoppingmall.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductRequestRepository productRequestRepository;

    @Override
    public Product findProductById(Integer productId) {

        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public ProductRequest createProduct(ProductRequest productRequest) {

        return productRequestRepository.save(productRequest);
    }
}
