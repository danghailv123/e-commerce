package com.huce.it.ecommerce.layer.application.domain.dao.elasticsearch;

import com.huce.it.ecommerce.layer.application.domain.model.dto.ProductTypeDto;
import com.huce.it.ecommerce.unitity.response.ResultResponse;

import java.io.IOException;

public interface IElasticProductTypeDao {

    ProductTypeDto getProfile(String id);

    ProductTypeDto save(ProductTypeDto productTypeDto) throws IOException;
    ResultResponse search(String keyWord, Integer limit, Integer page) ;

}
