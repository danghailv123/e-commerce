package com.huce.it.ecommerce.layer.application.domain.dao;

import com.huce.it.ecommerce.layer.application.domain.entity.ProductGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductGroupDao extends JpaRepository<ProductGroup,Integer> {
}
