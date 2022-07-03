package com.huce.it.ecommerce.layer.application.domain.dao.elasticsearch;

import com.huce.it.ecommerce.layer.application.domain.entity.ProductType;
import com.huce.it.ecommerce.layer.application.domain.model.dto.ProductDto;
import com.huce.it.ecommerce.layer.application.domain.model.dto.ProductGroupDto;
import com.huce.it.ecommerce.unitity.response.ResultResponse;

import java.io.IOException;

public interface IElasticProductGroupDao {

    ProductGroupDto getProfile(String id);

    ProductGroupDto save(ProductGroupDto productGroupDto) throws IOException;
    ResultResponse search(String keyWord, Integer limit, Integer page) ;

}
