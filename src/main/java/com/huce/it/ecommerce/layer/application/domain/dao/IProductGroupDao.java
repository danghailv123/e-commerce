package com.huce.it.ecommerce.layer.application.domain.dao;

import com.huce.it.ecommerce.layer.application.domain.entity.ProductGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IProductGroupDao extends JpaRepository<ProductGroup,Integer> {

    @Query("select pg.name from product_group pg where pg.id=:id")
    String getNameById(@Param("id") Integer id);
}
