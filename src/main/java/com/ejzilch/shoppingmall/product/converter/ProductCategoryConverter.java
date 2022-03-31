package com.ejzilch.shoppingmall.product.converter;

import com.ejzilch.shoppingmall.product.constant.ProductCategory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ProductCategoryConverter implements AttributeConverter<ProductCategory, String> {

    @Override
    public String convertToDatabaseColumn(ProductCategory productCategory) {
        return productCategory.toDbValue();
    }

    @Override
    public ProductCategory convertToEntityAttribute(String dbData) {
        return ProductCategory.from(dbData);
    }

}

