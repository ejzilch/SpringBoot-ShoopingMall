package com.ejzilch.shoppingmall.product.dao;

import com.ejzilch.shoppingmall.product.entity.ProductRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRequestRepository extends JpaRepository<ProductRequest, Integer> {

}
