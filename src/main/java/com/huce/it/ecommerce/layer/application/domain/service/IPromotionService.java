package com.huce.it.ecommerce.layer.application.domain.service;

import com.huce.it.ecommerce.layer.application.domain.entity.ProductGroup;
import com.huce.it.ecommerce.layer.application.domain.entity.Promotion;
import com.huce.it.ecommerce.layer.application.domain.model.dto.ProductTypeDto;
import com.huce.it.ecommerce.layer.application.domain.model.dto.PromotionDto;
import com.huce.it.ecommerce.unitity.response.ResultResponse;

import java.io.IOException;
import java.util.List;

public interface IPromotionService {
    PromotionDto create(PromotionDto promotionDto) throws IOException;

    void delete(Integer id) throws Exception;

    PromotionDto update(PromotionDto promotionDto) throws Exception;
    ResultResponse<PromotionDto> getPage(Integer page,Integer limit);
    PromotionDto getPromotion(Integer id);

    PromotionDto changeActive(PromotionDto promotionDto) throws Exception;

    ResultResponse getPage(Integer limit,Integer page ,String keyword);

    List<Promotion> getListPromotion();
}
