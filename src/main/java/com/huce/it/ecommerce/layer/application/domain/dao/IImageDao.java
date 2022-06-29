package com.huce.it.ecommerce.layer.application.domain.dao;

import com.huce.it.ecommerce.layer.application.domain.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IImageDao extends JpaRepository<Image,Integer> {
}
