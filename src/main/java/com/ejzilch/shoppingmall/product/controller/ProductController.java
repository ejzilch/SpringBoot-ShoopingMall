package com.ejzilch.shoppingmall.product.controller;

import com.ejzilch.shoppingmall.product.constant.ProductCategory;
import com.ejzilch.shoppingmall.product.dto.ProductQueryParams;
import com.ejzilch.shoppingmall.product.entity.Product;
import com.ejzilch.shoppingmall.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@RestController
@Validated
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

    @GetMapping("/products")
    public ResponseEntity<Page<Product>> findProducts(
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "CREATED_DATE") String orderByColumn,
            @RequestParam(defaultValue = "DESC") String sort,
            @RequestParam(defaultValue = "1") @Min(1) Integer pageNumber,
            @RequestParam(defaultValue = "3") @Min(3) @Max(20) Integer pageSize
    ) {

        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setSearch(search);

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize,
                (sort.equalsIgnoreCase("DESC") ?
                        Sort.by(Sort.Direction.DESC, orderByColumn) :
                        Sort.by(Sort.Direction.ASC, orderByColumn)));


        Page<Product> pageProductsList = productService
                .findProducts(productQueryParams, pageable);

        // last page json key:last value always false, temporary solution
        int contentSize = pageProductsList.getContent().size();
        long total = contentSize == pageSize ?
                contentSize : contentSize + pageSize - (contentSize % pageSize);

        PageImpl<Product> response = new PageImpl<>(pageProductsList.getContent(),
                pageable, total);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid Product product) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.createProduct(product));
    }

    @PutMapping("products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable @NotNull Integer productId,
                                                 @RequestBody @Valid Product product) {

        Product updateProduct = productService.updateProduct(productId, product);

        return updateProduct == null ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).build() :
                ResponseEntity.status(HttpStatus.OK).body(updateProduct);
    }

    @DeleteMapping("products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable @NotNull Integer productId) {

        productService.deleteProduct(productId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
