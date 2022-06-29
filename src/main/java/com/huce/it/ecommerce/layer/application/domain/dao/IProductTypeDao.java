package com.huce.it.ecommerce.layer.application.domain.dao;

import com.huce.it.ecommerce.layer.application.domain.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductTypeDao extends JpaRepository<ProductType,Integer> {
}
