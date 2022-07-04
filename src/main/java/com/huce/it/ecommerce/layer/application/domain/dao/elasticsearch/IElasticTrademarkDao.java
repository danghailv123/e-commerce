package com.huce.it.ecommerce.layer.application.domain.dao.elasticsearch;

import com.huce.it.ecommerce.layer.application.domain.model.dto.TrademarkDto;
import com.huce.it.ecommerce.unitity.response.ResultResponse;

import java.io.IOException;

public interface IElasticTrademarkDao {

    TrademarkDto getProfile(String id);

    TrademarkDto save(TrademarkDto trademarkDto) throws IOException;
    ResultResponse search(String keyWord, Integer limit, Integer page) ;

}
