package com.ejzilch.shoppingmall.product.dao;

import com.ejzilch.shoppingmall.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

}
