package com.huce.it.ecommerce.layer.application.domain.dao.elasticsearch;

import com.huce.it.ecommerce.layer.application.domain.model.dto.PromotionDto;
import com.huce.it.ecommerce.unitity.response.ResultResponse;

import java.io.IOException;

public interface IElasticPromotionDao {

    PromotionDto getProfile(String id);

    PromotionDto save(PromotionDto promotionDto) throws IOException;
    ResultResponse search(String keyWord, Integer limit, Integer page) ;

}
