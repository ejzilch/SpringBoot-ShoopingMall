package com.ejzilch.shoppingmall.product.entity;

import com.ejzilch.shoppingmall.product.constant.ProductCategory;
import com.ejzilch.shoppingmall.product.converter.ProductCategoryConverter;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    private String productName;

    @Convert(converter = ProductCategoryConverter.class)
    private ProductCategory category;

    private String imageUrl;
    private Integer price;
    private Integer stock;
    private String description;

    private Date createdDate;

    private Date lastModifiedDate;

}
