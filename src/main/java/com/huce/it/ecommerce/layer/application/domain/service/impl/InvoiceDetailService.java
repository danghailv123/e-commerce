package com.huce.it.ecommerce.layer.application.domain.service.impl;

import com.huce.it.ecommerce.config.Constants;
import com.huce.it.ecommerce.layer.application.domain.dao.IInvoiceDetailDao;
import com.huce.it.ecommerce.layer.application.domain.entity.InvoiceDetail;
import com.huce.it.ecommerce.layer.application.domain.entity.ProductGroup;
import com.huce.it.ecommerce.layer.application.domain.model.dto.InvoiceDetailDto;
import com.huce.it.ecommerce.layer.application.domain.model.dto.ProductGroupDto;
import com.huce.it.ecommerce.layer.application.domain.service.IInvoiceDetailService;
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

    @Override
    public List<InvoiceDetail> getALLByReceiptId(Integer id) {
        return iInvoiceDetailDao.findByProId(id);
    }


    @Override
    public ResultResponse<InvoiceDetailDto> getPageByReceiptId(Integer limit, Integer page ,Integer id) {
        Pageable pageable = PageRequest.of(page, 255, Sort.by("id").descending());
        Page<InvoiceDetail> invoiceDetails = iInvoiceDetailDao.findByProId(pageable , id);
        Long total = invoiceDetails.getTotalElements();
        List<InvoiceDetailDto> invoiceDetailDtos = new ArrayList<>();
        invoiceDetails.get().forEach(invoiceDetail -> {invoiceDetailDtos.add(Constants.SERIALIZER.convertValue(invoiceDetail,InvoiceDetailDto.class));});
        ResultResponse<InvoiceDetailDto> resultResponse = new ResultResponse<>();
        resultResponse.setData(invoiceDetailDtos);
        resultResponse.setLimit(limit);
        resultResponse.setPage(page);
        resultResponse.setTotal(total);
        resultResponse.setTotalPage((int) (total / limit));
        return resultResponse;
    }

}
