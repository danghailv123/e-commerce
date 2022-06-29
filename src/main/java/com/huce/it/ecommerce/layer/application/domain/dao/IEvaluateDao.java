package com.huce.it.ecommerce.layer.application.domain.dao;

import com.huce.it.ecommerce.layer.application.domain.entity.Evaluate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IEvaluateDao extends JpaRepository<Evaluate,Integer> {
    @Query("select ev from evaluate ev where ev.proId=:pro_id order by ev.createDate desc ")
    Page<Evaluate> getPageEvaluate(@Param("pro_id") Integer productId, Pageable pageable);
}
