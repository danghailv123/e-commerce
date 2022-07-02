package com.huce.it.ecommerce.layer.application.domain.service.impl;

import com.huce.it.ecommerce.config.Constants;
import com.huce.it.ecommerce.layer.application.domain.dao.IProductGroupDao;
import com.huce.it.ecommerce.layer.application.domain.dao.ITrademarkDao;
import com.huce.it.ecommerce.layer.application.domain.entity.ProductGroup;
import com.huce.it.ecommerce.layer.application.domain.entity.Trademark;
import com.huce.it.ecommerce.layer.application.domain.model.dto.ProductGroupDto;
import com.huce.it.ecommerce.layer.application.domain.model.dto.TrademarkDto;
import com.huce.it.ecommerce.layer.application.domain.service.ITrademarkService;
import com.huce.it.ecommerce.unitity.response.ResultResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrademarkService implements ITrademarkService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final ITrademarkDao iTrademarkDao;

    public TrademarkService(ITrademarkDao iTrademarkDao) {
        this.iTrademarkDao = iTrademarkDao;
    }


    @Override
    public TrademarkDto createTrademark(TrademarkDto trademarkDto) {
        Trademark trademark = Constants.SERIALIZER.convertValue(trademarkDto,Trademark.class);
        iTrademarkDao.save(trademark);
        logger.info("create product group " +trademark.getName());
        return Constants.SERIALIZER.convertValue(iTrademarkDao.save(trademark), TrademarkDto.class);
    }

    @Override
    public TrademarkDto changeActive(TrademarkDto trademarkDto) throws Exception {
        Trademark trademark = iTrademarkDao.getById(trademarkDto.getId());
        if (trademark==null){
            throw new Exception("product group not exist");
        }
        if (trademarkDto.getStatus()!=null)
            trademark.setStatus(trademarkDto.getStatus());

        return Constants.SERIALIZER.convertValue(iTrademarkDao.save(trademark),TrademarkDto.class);
    }

    @Override
    public TrademarkDto updateTrademark(TrademarkDto trademarkDto) throws Exception {
        Trademark trademark = iTrademarkDao.getById(trademarkDto.getId());
        if (trademark==null){
            throw new Exception("product group not exist");
        }
        if (trademarkDto.getName()!=null)
            trademark.setName(trademarkDto.getName());
        return Constants.SERIALIZER.convertValue(iTrademarkDao.save(trademark),TrademarkDto.class);
    }

    @Override
    public ResultResponse<TrademarkDto> getListTrademark(Integer limit, Integer page) {
        Pageable pageable = PageRequest.of(page, 255, Sort.by("id").descending());
        Page<Trademark> trademarks = iTrademarkDao.findAll(pageable);
        Long total = trademarks.getTotalElements();
        List<TrademarkDto> trademarkDtos = new ArrayList<>();
        trademarks.get().forEach(trademark -> {trademarkDtos.add(Constants.SERIALIZER.convertValue(trademark,TrademarkDto.class));});
        ResultResponse<TrademarkDto> resultResponse = new ResultResponse<>();
        resultResponse.setData(trademarkDtos);
        resultResponse.setLimit(limit);
        resultResponse.setPage(page);
        resultResponse.setTotal(total);
        resultResponse.setTotalPage((int) (total / limit));
        return resultResponse;
    }

    @Override
    public TrademarkDto getTrademark(Integer id) {
        return Constants.SERIALIZER.convertValue(iTrademarkDao.getById(id),TrademarkDto.class);
    }
}
