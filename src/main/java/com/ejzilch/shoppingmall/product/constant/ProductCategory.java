package com.ejzilch.shoppingmall.product.constant;

public enum ProductCategory {

    FOOD,
    CAR,
    BOOK,
    UNKNOWN;

    public String toDbValue() {
        return this.name().toLowerCase();
    }

    public static ProductCategory from(String category) {
        // Note: error if null, error if not "ACTIVE" nor "INACTIVE"
        return ProductCategory.valueOf(category.toUpperCase());
    }
}
