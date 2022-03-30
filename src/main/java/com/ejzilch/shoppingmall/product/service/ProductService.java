package com.ejzilch.shoppingmall.product.service;

import com.ejzilch.shoppingmall.product.dao.ProductDao;
import com.ejzilch.shoppingmall.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public Product findProductById(Integer productId) {

        Optional<Product> product = productDao.findById(productId);

        return product.orElse(null);

    }

}
