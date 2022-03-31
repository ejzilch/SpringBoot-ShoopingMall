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
@Entity(name = "product")
@EntityListeners(AuditingEntityListener.class)
public class ProductRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @NotNull
    private String productName;

    @NotNull
    @Convert(converter = ProductCategoryConverter.class)
    private ProductCategory category;

    @NotNull
    private String imageUrl;

    @NotNull
    private Integer price;

    @NotNull
    private Integer stock;

    private String description;

    @Column(updatable = false)
    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date lastModifiedDate;

}
