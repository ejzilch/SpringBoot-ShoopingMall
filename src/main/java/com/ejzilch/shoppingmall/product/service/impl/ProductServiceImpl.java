package com.ejzilch.shoppingmall.product.service.impl;

import com.ejzilch.shoppingmall.product.dao.ProductRepository;
import com.ejzilch.shoppingmall.product.dto.ProductQueryParams;
import com.ejzilch.shoppingmall.product.entity.Product;
import com.ejzilch.shoppingmall.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product findProductById(Integer productId) {

        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public List<Product> findProducts(ProductQueryParams productQueryParams, Pageable pageable) {
        return productRepository
                .findProductsByCategoryAndSearch(productQueryParams, pageable);
    }

    @Override
    public Product createProduct(Product product) {

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Integer productId, Product product) {

        Product findProduct = productRepository.findById(productId).orElse(null);

        if (findProduct == null) return null;

        product.setProductId(productId);
        product.setCreatedDate(findProduct.getCreatedDate());

        productRepository.save(product);

        return productRepository.findById(product.getProductId()).orElse(null);
    }

    @Override
    public void deleteProduct(Integer productId) {

        try {
            productRepository.deleteById(productId);
        } catch (Exception e) {
            // JPA will check for existence before deleting
            // Do nothing
        }
    }
}
