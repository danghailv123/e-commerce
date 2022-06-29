package com.huce.it.ecommerce.layer.application.domain.service.impl;

import com.huce.it.ecommerce.layer.application.domain.dao.IInvoiceDetailDao;
import com.huce.it.ecommerce.layer.application.domain.model.dto.InvoiceDetailDto;
import com.huce.it.ecommerce.layer.application.domain.service.IInvoiceDetailService;
import com.huce.it.ecommerce.unitity.response.ResultResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class InvoiceDetailService implements IInvoiceDetailService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final IInvoiceDetailDao iInvoiceDetailDao;

    public InvoiceDetailService(IInvoiceDetailDao iInvoiceDetailDao) {
        this.iInvoiceDetailDao = iInvoiceDetailDao;
    }

    @Override
    public InvoiceDetailDto create(InvoiceDetailDto invoiceDetailDto) {
        return null;
    }

    @Override
    public InvoiceDetailDto update(InvoiceDetailDto invoiceDetailDto) {
        return null;
    }

    @Override
    public void delete(InvoiceDetailDto invoiceDetailDto) {

    }

    @Override
    public ResultResponse<InvoiceDetailDto> getAll() {
        return null;
    }
}
