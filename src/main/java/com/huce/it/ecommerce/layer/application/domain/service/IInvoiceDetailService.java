package com.huce.it.ecommerce.layer.application.domain.service;

import com.huce.it.ecommerce.layer.application.domain.model.dto.InvoiceDetailDto;
import com.huce.it.ecommerce.unitity.response.ResultResponse;

public interface IInvoiceDetailService {
    InvoiceDetailDto create(InvoiceDetailDto invoiceDetailDto);
    InvoiceDetailDto update(InvoiceDetailDto invoiceDetailDto);
    void delete(InvoiceDetailDto invoiceDetailDto);
    ResultResponse<InvoiceDetailDto> getAll();
}
