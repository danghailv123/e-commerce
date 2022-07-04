package com.huce.it.ecommerce.layer.application.domain.dao.elasticsearch;

import com.huce.it.ecommerce.layer.application.domain.model.dto.AccountDto;
import com.huce.it.ecommerce.unitity.response.ResultResponse;

import java.io.IOException;

public interface IElasticUserDao {

    AccountDto getProfile(String id);

    AccountDto save(AccountDto accountDto) throws IOException;
    ResultResponse search(String keyWord, Integer limit, Integer page) ;

}
