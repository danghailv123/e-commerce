package com.huce.it.ecommerce.layer.presentation.controller;

import com.huce.it.ecommerce.layer.application.domain.model.dto.ReceiptDto;
import com.huce.it.ecommerce.layer.application.domain.service.IReceiptService;
import com.huce.it.ecommerce.unitity.response.Response;
import com.huce.it.ecommerce.unitity.response.ResponseFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/huce/receipt")
public class ReceiptController {

    private final IReceiptService iReceiptService;

    public ReceiptController(IReceiptService iReceiptService) {
        this.iReceiptService = iReceiptService;
    }


    @PostMapping("create")
    public Response createReceipt(@RequestBody ReceiptDto receiptDto){
        try {
            return ResponseFactory.getSuccessResponse(Response.SUCCESS,iReceiptService.create(receiptDto));
        }catch (Exception exception){
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

    @DeleteMapping("delete")
    public Response deleteReceipt(@RequestParam(name = "id")Integer id){
        try {
            iReceiptService.delete(id);
            return ResponseFactory.getSuccessResponse(Response.SUCCESS);
        }catch (Exception exception){
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

    @PostMapping("update")
    public Response updateReceipt(@RequestBody ReceiptDto receiptDto){
        try {
            return ResponseFactory.getSuccessResponse(Response.SUCCESS,iReceiptService.update(receiptDto));
        }catch (Exception exception){
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

    @GetMapping("get-page")
    public Response getPageReceipt(@RequestParam(name = "page", required = false, defaultValue ="0")Integer page,
                                    @RequestParam(name = "limit",required = false,defaultValue = "10")Integer limit){
        try {
            return ResponseFactory.getSuccessResponse(Response.SUCCESS, iReceiptService.getPage(page, limit));
        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

}
