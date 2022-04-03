package com.ejzilch.shoppingmall.product.dto;

import com.ejzilch.shoppingmall.product.constant.ProductCategory;
import lombok.Data;
import org.springframework.data.domain.Pageable;

@Data
public class ProductQueryParams {

    private ProductCategory category;

    private String search;

}
