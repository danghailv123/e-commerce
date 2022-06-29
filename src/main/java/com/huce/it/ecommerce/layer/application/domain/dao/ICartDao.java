package com.huce.it.ecommerce.layer.application.domain.dao;

import com.huce.it.ecommerce.layer.application.domain.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartDao extends JpaRepository<Cart,Integer> {
}
