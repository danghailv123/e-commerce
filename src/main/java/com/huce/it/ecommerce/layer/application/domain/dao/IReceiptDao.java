package com.huce.it.ecommerce.layer.application.domain.dao;

import com.huce.it.ecommerce.layer.application.domain.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReceiptDao extends JpaRepository<Receipt,Integer> {
}
