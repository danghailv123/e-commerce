package com.huce.it.ecommerce.layer.application.domain.dao;

import com.huce.it.ecommerce.layer.application.domain.entity.InvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInvoiceDetailDao extends JpaRepository<InvoiceDetail,Integer> {
}
