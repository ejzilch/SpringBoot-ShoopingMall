package com.ejzilch.shoppingmall.product.dao;

import com.ejzilch.shoppingmall.product.dto.ProductQueryParams;
import com.ejzilch.shoppingmall.product.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value =
            "SELECT product_id, product_name, category, image_url, price," +
                    " stock, description, created_date, last_modified_date" +
                    " FROM PRODUCT p WHERE 1 = 1" +
                    " AND if(:#{#productQueryParams.getCategory} != ''," +
                        " p.CATEGORY = :#{#productQueryParams.getCategory?.name}, 1 = 1)" +
                    " AND if(:#{#productQueryParams.getSearch} != ''," +
                        " p.PRODUCT_NAME LIKE %:#{#productQueryParams.getSearch}%" +
                        " OR p.DESCRIPTION LIKE %:#{#productQueryParams.getSearch}%, 1 = 1)" +
            " ORDER BY :#{#pageable}",
            countQuery =
                 "SELECT count(*)" +
                         " FROM PRODUCT p WHERE 1 = 1" +
                         " AND if(:#{#productQueryParams.getCategory} != ''," +
                         " p.CATEGORY = :#{#productQueryParams.getCategory?.name}, 1 = 1)" +
                         " AND if(:#{#productQueryParams.getSearch} != ''," +
                         " p.PRODUCT_NAME LIKE %:#{#productQueryParams.getSearch}%" +
                         " OR p.DESCRIPTION LIKE %:#{#productQueryParams.getSearch}%, 1 = 1)" +
                         " ORDER BY :#{#pageable}",
            nativeQuery = true)
    List<Product> findProductsByCategoryAndSearch(@Param("productQueryParams") ProductQueryParams productQueryParams,
                                                       Pageable pageable);
}
