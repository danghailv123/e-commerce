package com.huce.it.ecommerce.layer.presentation.controller;

import com.huce.it.ecommerce.layer.application.domain.model.dto.InvoiceDetailDto;
import com.huce.it.ecommerce.layer.application.domain.model.dto.PromotionDto;
import com.huce.it.ecommerce.layer.application.domain.service.IInvoiceDetailService;
import com.huce.it.ecommerce.unitity.response.Response;
import com.huce.it.ecommerce.unitity.response.ResponseFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/huce/invoice-detail")
public class InvoiceDetailController {
    private final IInvoiceDetailService iInvoiceDetailService;

    public InvoiceDetailController(IInvoiceDetailService iInvoiceDetailService) {
        this.iInvoiceDetailService = iInvoiceDetailService;
    }


    @PostMapping("create")
    public Response createInvoiceDetail(@RequestBody InvoiceDetailDto invoiceDetailDto) {
        try {
            return ResponseFactory.getSuccessResponse(Response.SUCCESS, iInvoiceDetailService.create(invoiceDetailDto));
        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

    @DeleteMapping("delete")
    public Response deleteInvoiceDetail(@RequestBody InvoiceDetailDto invoiceDetailDto) {
        try {
            iInvoiceDetailService.delete(invoiceDetailDto);
            return ResponseFactory.getSuccessResponse(Response.SUCCESS);
        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

    @PostMapping("update")
    public Response updateInvoiceDetail(@RequestBody InvoiceDetailDto invoiceDetailDto) {
        try {
            return ResponseFactory.getSuccessResponse(Response.SUCCESS, iInvoiceDetailService.update(invoiceDetailDto));
        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }


}
