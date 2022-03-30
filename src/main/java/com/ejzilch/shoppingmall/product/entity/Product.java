package com.ejzilch.shoppingmall.product.entity;

import com.ejzilch.shoppingmall.product.constant.ProductCategory;
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

    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    private String imageUrl;
    private Integer price;
    private Integer stock;
    private String description;

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date lastModifiedDate;

}
