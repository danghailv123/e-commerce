package com.huce.it.ecommerce.layer.application.domain.service.impl;

import com.huce.it.ecommerce.config.Constants;
import com.huce.it.ecommerce.layer.application.domain.dao.IEvaluateDao;
import com.huce.it.ecommerce.layer.application.domain.entity.Evaluate;
import com.huce.it.ecommerce.layer.application.domain.model.dto.EvaluateDto;
import com.huce.it.ecommerce.layer.application.domain.service.IEvaluateService;
import com.huce.it.ecommerce.unitity.response.ResultResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EvaluateService implements IEvaluateService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final IEvaluateDao iEvaluateDao;

    public EvaluateService(IEvaluateDao iEvaluateDao) {
        this.iEvaluateDao = iEvaluateDao;
    }

    @Override
    public EvaluateDto create(EvaluateDto evaluateDto) {
        Evaluate evaluate = Constants.SERIALIZER.convertValue(evaluateDto, Evaluate.class);
        logger.info("create evaluate");
        return Constants.SERIALIZER.convertValue(iEvaluateDao.save(evaluate), EvaluateDto.class);
    }

    @Override
    public void delete(Integer id) {
        logger.info("remove evaluate id :{}", id);
        iEvaluateDao.deleteById(id);
    }

    @Override
    public ResultResponse<EvaluateDto> getPage(Integer page, Integer limit, Integer proId) {
        Pageable pageable = PageRequest.of(page, limit);
        Page<Evaluate> evaluatePage = iEvaluateDao.getPageEvaluate(proId, pageable);
        List<EvaluateDto> evaluateDtos = new ArrayList<>();
        evaluatePage.getContent().forEach(evaluate -> {
            evaluateDtos.add(Constants.SERIALIZER.convertValue(evaluate,EvaluateDto.class));
        });
        ResultResponse<EvaluateDto> resultResponse = new ResultResponse<>();
        resultResponse.setPage(page);
        resultResponse.setLimit(limit);
        resultResponse.setTotal(evaluatePage.getTotalElements());
        resultResponse.setData(evaluateDtos);
        return resultResponse;
    }
}
