package com.ejzilch.shoppingmall.product.controller;

import com.ejzilch.shoppingmall.product.entity.Product;
import com.ejzilch.shoppingmall.product.entity.ProductRequest;
import com.ejzilch.shoppingmall.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> findProductById(@PathVariable Integer productId) {

        Product product = productService.findProductById(productId);

        return product == null ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).build() :
                ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductRequest> createProduct(@RequestBody @Valid ProductRequest productRequest) {

        ProductRequest createProductRequest = productService.createProduct(productRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(createProductRequest);
    }
}
