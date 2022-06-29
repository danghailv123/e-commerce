package com.huce.it.ecommerce.layer.application.domain.dao;

import com.huce.it.ecommerce.layer.application.domain.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IAccountDao extends JpaRepository<Account,Integer> {
    Account findAccountByEmail(String email);
    @Query("select ac from user_account ac ")
    Page<Account> findAll(Pageable pageable);
}
