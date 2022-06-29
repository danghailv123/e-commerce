package com.huce.it.ecommerce.layer.application.domain.dao;

import com.huce.it.ecommerce.layer.application.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IProductDao extends JpaRepository<Product,Integer> {
}
