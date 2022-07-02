package com.huce.it.ecommerce.layer.application.domain.service;

import com.huce.it.ecommerce.layer.application.domain.model.dto.ProductGroupDto;
import com.huce.it.ecommerce.layer.application.domain.model.dto.TrademarkDto;
import com.huce.it.ecommerce.unitity.response.ResultResponse;

public interface ITrademarkService {

    TrademarkDto createTrademark(TrademarkDto trademarkDto);
    TrademarkDto changeActive(TrademarkDto trademarkDto) throws Exception;
    TrademarkDto updateTrademark(TrademarkDto trademarkDto) throws Exception;
    ResultResponse<TrademarkDto> getListTrademark(Integer limit, Integer page);
    TrademarkDto getTrademark(Integer id);
}
