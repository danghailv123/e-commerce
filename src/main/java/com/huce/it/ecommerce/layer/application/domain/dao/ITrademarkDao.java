package com.huce.it.ecommerce.layer.application.domain.dao;

import com.huce.it.ecommerce.layer.application.domain.entity.Trademark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITrademarkDao extends JpaRepository<Trademark,Integer> {
}
