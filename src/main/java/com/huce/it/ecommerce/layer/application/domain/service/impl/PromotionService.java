package com.huce.it.ecommerce.layer.application.domain.service.impl;

import com.huce.it.ecommerce.config.Constants;
import com.huce.it.ecommerce.layer.application.domain.dao.IPromotionDao;
import com.huce.it.ecommerce.layer.application.domain.dao.elasticsearch.IElasticPromotionDao;
import com.huce.it.ecommerce.layer.application.domain.entity.ProductType;
import com.huce.it.ecommerce.layer.application.domain.entity.Promotion;
import com.huce.it.ecommerce.layer.application.domain.model.dto.ProductTypeDto;
import com.huce.it.ecommerce.layer.application.domain.model.dto.PromotionDto;
import com.huce.it.ecommerce.layer.application.domain.service.IPromotionService;
import com.huce.it.ecommerce.unitity.response.ResultResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PromotionService implements IPromotionService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final IPromotionDao iPromotionDao;
    private final IElasticPromotionDao iElasticPromotionDao;

    public PromotionService(IPromotionDao iPromotionDao, IElasticPromotionDao iElasticPromotionDao) {
        this.iPromotionDao = iPromotionDao;
        this.iElasticPromotionDao = iElasticPromotionDao;
    }

    @Override
    public PromotionDto create(PromotionDto promotionDto) throws IOException {
        Promotion promotion = Constants.SERIALIZER.convertValue(promotionDto, Promotion.class);
        Promotion promotionEs = iPromotionDao.save(promotion);
        iElasticPromotionDao.save(Constants.SERIALIZER.convertValue(promotionEs, PromotionDto.class));

        return Constants.SERIALIZER.convertValue(promotionEs, PromotionDto.class);
    }

    @Override
    public void delete(Integer id) throws Exception {
        Promotion promotion = iPromotionDao.getById(id);
        if (promotion==null) throw new Exception("Promotion not exist database");
        iPromotionDao.delete(promotion);
        logger.info("delete promotion id = {}",promotion.getId());
    }

    @Override
    public PromotionDto update(PromotionDto promotionDto) throws Exception {
        Promotion promotion = iPromotionDao.getById(promotionDto.getId());
        if (promotion==null) throw new Exception("Promotion not exist database");
        if (promotionDto.getStatus()!= null) promotion.setStatus(promotionDto.getStatus());
        if (promotionDto.getDescription()!= null) promotion.setDescription(promotionDto.getDescription());
        if (promotionDto.getFromDate()!= null) promotion.setFromDate(promotionDto.getFromDate());
        if (promotionDto.getImage()!= null) promotion.setImage(promotionDto.getImage());
        if (promotionDto.getPercent()!= null) promotion.setPercent(promotionDto.getPercent());
        if (promotionDto.getToDate()!= null) promotion.setToDate(promotionDto.getToDate());
        iElasticPromotionDao.save(Constants.SERIALIZER.convertValue(promotion, PromotionDto.class));
        return Constants.SERIALIZER.convertValue(iPromotionDao.save(promotion),PromotionDto.class);
    }

    @Override
    public ResultResponse<PromotionDto> getPage(Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page,limit,Sort.by(Sort.Direction.DESC,"toDate"));
        Page<Promotion> promotionPage = iPromotionDao.findAll(pageable);
        List<PromotionDto> promotionDtos = new ArrayList<>();
        promotionPage.getContent().forEach(promotion -> {
            promotionDtos.add(Constants.SERIALIZER.convertValue(promotion,PromotionDto.class));
        });
        ResultResponse<PromotionDto> resultResponse = new ResultResponse<>();
        resultResponse.setPage(page);
        resultResponse.setData(promotionDtos);
        resultResponse.setLimit(limit);
        resultResponse.setTotal(promotionPage.getTotalElements());
        return resultResponse;
    }

    @Override
    public PromotionDto getPromotion(Integer id) {
        return Constants.SERIALIZER.convertValue(iPromotionDao.getById(id),PromotionDto.class);
    }

    @Override
    public PromotionDto changeActive(PromotionDto promotionDto) throws Exception {
        Promotion promotion = iPromotionDao.getById(promotionDto.getId());
        if (promotion==null){
            throw new Exception("promotion not exist");
        }
        if (promotionDto.getStatus()!=null){
            promotion.setStatus(promotionDto.getStatus());
            iElasticPromotionDao.save(Constants.SERIALIZER.convertValue(promotion, PromotionDto.class));
        }
        return Constants.SERIALIZER.convertValue(iPromotionDao.save(promotion),PromotionDto.class);
    }

    @Override
    public ResultResponse getPage(Integer limit, Integer page, String keyword) {
        return iElasticPromotionDao.search(keyword , limit , page);
    }

    @Override
    public List<Promotion> getListPromotion() {
        return iPromotionDao.findAll();
    }
}
