package com.ejzilch.shoppingmall.product.entity;

import com.ejzilch.shoppingmall.product.constant.ProductCategory;
import com.ejzilch.shoppingmall.product.converter.ProductCategoryConverter;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date lastModifiedDate;

}
