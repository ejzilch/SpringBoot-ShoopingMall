package com.ejzilch.shoppingmall.product.service.impl;

import com.ejzilch.shoppingmall.product.constant.ProductCategory;
import com.ejzilch.shoppingmall.product.dto.ProductQueryParams;
import com.ejzilch.shoppingmall.product.entity.Product;
import com.ejzilch.shoppingmall.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    ProductService productService;

    @Test
    public void findProductById() throws ParseException {
        Product productIsNull = productService.findProductById(18);

        assertNull(productIsNull);

        Product productNotNull = productService.findProductById(1);
        assertNotNull(productNotNull);
        assertNotNull(productNotNull.getProductId());
        assertNotNull(productNotNull.getProductName());
        assertNotNull(productNotNull.getCategory());
        assertNotNull(productNotNull.getImageUrl());
        assertNotNull(productNotNull.getPrice());
        assertNotNull(productNotNull.getStock());
        assertNotNull(productNotNull.getLastModifiedDate());
        assertNotNull(productNotNull.getCreatedDate());

        assertEquals(1, productNotNull.getProductId());
        assertEquals("蘋果（澳洲）", productNotNull.getProductName());
        assertEquals("FOOD", productNotNull.getCategory().name());
        assertEquals("https://cdn.pixabay.com/photo/2016/11/30/15/00/apples-1872997_1280.jpg", productNotNull.getImageUrl());
        assertEquals(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        .parse("2022-03-22 18:00:00").getTime(),
                productNotNull.getLastModifiedDate().getTime());
        assertEquals(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        .parse("2022-03-19 17:00:00").getTime(),
                productNotNull.getCreatedDate().getTime());
    }

    @Test
    public void findProducts() {

        int pageNumber = 0;
        int pageSize = 5;
        String orderByColumn = "CREATED_DATE";

        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setCategory(ProductCategory.FOOD);
        productQueryParams.setSearch("甜");

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, orderByColumn));

        Page<Product> pageProductsList = productService.findProducts(productQueryParams, pageable);

        int contentSize = pageProductsList.getContent().size();
        long total = contentSize == pageSize ?
                contentSize : contentSize + pageSize - (contentSize % pageSize);

        PageImpl<Product> response = new PageImpl<>(pageProductsList.getContent(),
                pageable, total);

        assertNotNull(response);
        assertEquals(1, response.getContent().size());
        assertEquals(0, response.getNumber());
        assertEquals(1, response.getTotalPages());
        assertEquals(pageSize, response.getSize());
        assertEquals(3, response.getContent().get(0).getProductId());
    }

    @Test
    public void createProduct() {

        Product product = new Product();

        product.setProductName("香蕉");
        product.setCategory(ProductCategory.FOOD);
        product.setImageUrl("https://cdn.pixabay.com/photo/2016/11/30/15/00/apples-1872997_1280.jpg");
        product.setPrice(5);
        product.setStock(5);
        product.setDescription("台灣香蕉");

        Product createProduct = productService.createProduct(product);

        assertNotNull(createProduct.getProductId());
        assertEquals(product.getProductName(), createProduct.getProductName());
        assertEquals(product.getCategory().name(), createProduct.getCategory().name());
        assertEquals(product.getImageUrl(), createProduct.getImageUrl());
        assertEquals(product.getPrice(), createProduct.getPrice());
        assertNotNull(createProduct.getCreatedDate());
        assertNotNull(createProduct.getLastModifiedDate());
    }

    @Transactional
    @Test
    public void updateProduct() {

        Product product = productService.findProductById(1);
        Product updatedProduct = null;

        if (product != null) {
            product.setProductName("大蘋果");
            product.setDescription("好吃的大蘋果!");
            updatedProduct = productService.updateProduct(product.getProductId(), product);
        }

        assertNotNull(updatedProduct);
        assertEquals("大蘋果", product.getProductName());
        assertEquals("好吃的大蘋果!", product.getDescription());

    }

    @Transactional
    @Test
    public void deleteProduct() {

        productService.deleteProduct(1);

        assertNull(productService.findProductById(1));

    }
}