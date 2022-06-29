package com.huce.it.ecommerce.layer.application.domain.service;

import com.huce.it.ecommerce.layer.application.domain.model.dto.ReceiptDto;
import com.huce.it.ecommerce.unitity.response.ResultResponse;

public interface IReceiptService {
    ReceiptDto create(ReceiptDto receiptDto);

    void delete(Integer id) throws Exception;
    ReceiptDto update(ReceiptDto receiptDto) throws Exception;

    ResultResponse<ReceiptDto> getPage(Integer page,Integer limit);

}
