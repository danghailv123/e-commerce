package com.huce.it.ecommerce.layer.application.domain.dao;

import com.huce.it.ecommerce.layer.application.domain.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPromotionDao extends JpaRepository<Promotion,Integer> {
}
