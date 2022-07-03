package com.huce.it.ecommerce.layer.application.domain.service;

import com.huce.it.ecommerce.layer.application.domain.entity.InvoiceDetail;
import com.huce.it.ecommerce.layer.application.domain.model.dto.InvoiceDetailDto;
import com.huce.it.ecommerce.unitity.response.ResultResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IInvoiceDetailService {
    InvoiceDetailDto create(InvoiceDetailDto invoiceDetailDto);
    InvoiceDetailDto update(InvoiceDetailDto invoiceDetailDto);
    void delete(InvoiceDetailDto invoiceDetailDto);
    ResultResponse<InvoiceDetailDto> getAll();
    List<InvoiceDetail> getALLByReceiptId(Integer id);
    ResultResponse<InvoiceDetailDto> getPageByReceiptId(Integer limit, Integer page, Integer id);
}
