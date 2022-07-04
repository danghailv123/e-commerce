package com.huce.it.ecommerce.layer.application.domain.service;

import com.huce.it.ecommerce.layer.application.domain.entity.ProductGroup;
import com.huce.it.ecommerce.layer.application.domain.entity.Trademark;
import com.huce.it.ecommerce.layer.application.domain.model.dto.ProductGroupDto;
import com.huce.it.ecommerce.layer.application.domain.model.dto.TrademarkDto;
import com.huce.it.ecommerce.unitity.response.ResultResponse;

import java.io.IOException;
import java.util.List;

public interface ITrademarkService {

    TrademarkDto createTrademark(TrademarkDto trademarkDto) throws IOException;
    TrademarkDto changeActive(TrademarkDto trademarkDto) throws Exception;
    TrademarkDto updateTrademark(TrademarkDto trademarkDto) throws Exception;
    ResultResponse<TrademarkDto> getListTrademark(Integer limit, Integer page);
    TrademarkDto getTrademark(Integer id);

    ResultResponse getPage(Integer limit,Integer page ,String keyword);

    List<Trademark> getLisTrademark();
}
