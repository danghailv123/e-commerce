package com.huce.it.ecommerce.layer.application.domain.service;

import com.huce.it.ecommerce.layer.application.domain.entity.Account;
import com.huce.it.ecommerce.layer.application.domain.model.dto.AccountDto;
import com.huce.it.ecommerce.layer.application.domain.model.dto.ProductGroupDto;
import com.huce.it.ecommerce.unitity.response.ResultResponse;

public interface IAccountService {
    void createAccount(AccountDto account);

    Account getAccountByEmail(String email);

    ResultResponse<Account> getAccounts(Integer limit, Integer page);

    void changePasswordAccount(AccountDto account) throws Exception;

    void activeAccount(AccountDto account) throws Exception;

    Account getAccountById(Integer id);

    void updateAccount(AccountDto account) throws Exception;
}
