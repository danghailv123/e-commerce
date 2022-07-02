package com.huce.it.ecommerce.layer.application.domain.service;

import com.huce.it.ecommerce.layer.application.domain.model.dto.PromotionDto;
import com.huce.it.ecommerce.unitity.response.ResultResponse;

public interface IPromotionService {
    PromotionDto create(PromotionDto promotionDto);

    void delete(Integer id) throws Exception;

    PromotionDto update(PromotionDto promotionDto) throws Exception;
    ResultResponse<PromotionDto> getPage(Integer page,Integer limit);
    PromotionDto getPromotion(Integer id);
}
