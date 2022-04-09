package com.ejzilch.shoppingmall.product.dao;

import com.ejzilch.shoppingmall.product.dto.ProductQueryParams;
import com.ejzilch.shoppingmall.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(nativeQuery = true,
            value = "SELECT product_id, product_name, category, image_url, price," +
                    " stock, description, created_date, last_modified_date" +
                    " FROM PRODUCT p WHERE 1 = 1" +
                    " AND" +
                        " CASE WHEN :#{#productQueryParams.getCategory} != ''" +
                            " THEN p.CATEGORY = :#{#productQueryParams.getCategory?.name}" +
                            " ELSE 1 = 1" +
                        " END"+
                    " AND" +
                        " CASE WHEN :#{#productQueryParams.getSearch} != ''" +
                            " THEN p.PRODUCT_NAME LIKE %:#{#productQueryParams.getSearch}%" +
                            " OR p.DESCRIPTION LIKE %:#{#productQueryParams.getSearch}%" +
                            " ELSE 1 = 1" +
                        " END",
            countQuery = "SELECT count(*)" +
                    " FROM PRODUCT p WHERE 1 = 1" +
                    " AND" +
                        " CASE WHEN :#{#productQueryParams.getCategory} != ''" +
                            " THEN p.CATEGORY = :#{#productQueryParams.getCategory?.name}" +
                            " ELSE 1 = 1" +
                        " END"+
                    " AND" +
                        " CASE WHEN :#{#productQueryParams.getSearch} != ''" +
                            " THEN p.PRODUCT_NAME LIKE %:#{#productQueryParams.getSearch}%" +
                            " OR p.DESCRIPTION LIKE %:#{#productQueryParams.getSearch}%" +
                            " ELSE 1 = 1" +
                        " END")
    Page<Product> findProductsByCategoryAndSearch(@Param("productQueryParams") ProductQueryParams productQueryParams,
                                                  Pageable pageable);
}
