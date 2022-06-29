package com.huce.it.ecommerce.layer.application.domain.service;

import com.huce.it.ecommerce.layer.application.domain.entity.Evaluate;
import com.huce.it.ecommerce.layer.application.domain.model.dto.EvaluateDto;
import com.huce.it.ecommerce.unitity.response.ResultResponse;

public interface IEvaluateService {
    EvaluateDto create(EvaluateDto evaluateDto);

    void delete(Integer id);

    ResultResponse<EvaluateDto> getPage(Integer page, Integer limit,Integer proId);
}
