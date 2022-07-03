package com.huce.it.ecommerce.layer.application.domain.dao;

import com.huce.it.ecommerce.layer.application.domain.entity.InvoiceDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IInvoiceDetailDao extends JpaRepository<InvoiceDetail,Integer> {
    List<InvoiceDetail> findByProId(Integer pro_id);
    Page<InvoiceDetail> findByProId(Pageable pageable, Integer pro_id);
}
