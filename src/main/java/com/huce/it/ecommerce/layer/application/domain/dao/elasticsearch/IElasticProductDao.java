package com.huce.it.ecommerce.layer.application.domain.dao.elasticsearch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.huce.it.ecommerce.layer.application.domain.model.dto.ProductDto;
import com.huce.it.ecommerce.unitity.response.ResultResponse;

import java.io.IOException;
import java.util.List;

public interface IElasticProductDao {
    ProductDto getProfile( String productId);

    ProductDto save(ProductDto productDto) throws IOException;
    ResultResponse search(String keyWord, Integer limit, Integer page) ;

}
