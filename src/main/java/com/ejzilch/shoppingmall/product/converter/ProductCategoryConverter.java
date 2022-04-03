package com.ejzilch.shoppingmall.product.converter;

import com.ejzilch.shoppingmall.product.constant.ProductCategory;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Locale;

@Converter(autoApply = true)
@Component
public class ProductCategoryConverter implements AttributeConverter<ProductCategory, String>,
        org.springframework.core.convert.converter.Converter<String, ProductCategory> {

    @Override
    public String convertToDatabaseColumn(ProductCategory productCategory) {
        return productCategory.toDbValue();
    }

    @Override
    public ProductCategory convertToEntityAttribute(String dbData) {
        return ProductCategory.from(dbData);
    }

    @Override
    public ProductCategory convert(String source) {
        try {

            return source.isEmpty() ?
                    null :
                    ProductCategory.valueOf(source.trim().toUpperCase(Locale.US));

        } catch(Exception e) {

            return ProductCategory.UNKNOWN; //

        }
    }
}

