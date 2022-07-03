package com.huce.it.ecommerce.layer.application.domain.mapper;

import com.huce.it.ecommerce.layer.application.domain.entity.InvoiceDetail;
import com.huce.it.ecommerce.layer.application.domain.entity.Receipt;
import com.huce.it.ecommerce.layer.application.domain.model.dto.ReceiptDto;
import com.huce.it.ecommerce.layer.application.domain.service.IInvoiceDetailService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class ReceiptMapper {

    private final IInvoiceDetailService iInvoiceDetailService;


    public ReceiptMapper(IInvoiceDetailService iInvoiceDetailService) {
        this.iInvoiceDetailService = iInvoiceDetailService;
    }

    public ReceiptDto mapReceiptToReceiptDto(Receipt from){

        ReceiptDto to = new ReceiptDto();

        to.setId(from.getId());
        to.setCreateDate(from.getCreateDate());

        AtomicReference<Float> tong = new AtomicReference<>(0f);

        List<InvoiceDetail> invoiceDetails = iInvoiceDetailService.getALLByReceiptId(from.getId());

        invoiceDetails.forEach(invoiceDetail -> {
            tong.updateAndGet(v -> v + invoiceDetail.getPrice());
        });

        to.setSum(tong.get());
        return to;

    }
}
