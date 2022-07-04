package com.huce.it.ecommerce.layer.presentation.controller;

import com.huce.it.ecommerce.layer.application.domain.model.dto.TrademarkDto;
import com.huce.it.ecommerce.layer.application.domain.service.ITrademarkService;
import com.huce.it.ecommerce.unitity.response.Response;
import com.huce.it.ecommerce.unitity.response.ResponseFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("huce/trademark")
public class TrademarkController {

    private final ITrademarkService iTrademarkService;

    public TrademarkController(ITrademarkService iTrademarkService) {
        this.iTrademarkService = iTrademarkService;
    }

    @PostMapping("/create")
    public Response createTrademark(@RequestBody TrademarkDto trademarkDto) {
        try {
            return ResponseFactory.getSuccessResponse(Response.SUCCESS, iTrademarkService.createTrademark(trademarkDto));
        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

    @PostMapping("/update")
    public Response updateTrademark(@RequestBody TrademarkDto trademarkDto) {
        try {
            return ResponseFactory.getSuccessResponse(Response.SUCCESS, iTrademarkService.updateTrademark(trademarkDto));
        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

    @PostMapping("change-status")
    public Response changeStatusTrademark(@RequestBody TrademarkDto trademarkDto) {
        try {
            return ResponseFactory.getSuccessResponse(Response.SUCCESS, iTrademarkService.changeActive(trademarkDto));
        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

    @GetMapping("/get-list")
    public Response getListTrademark(@RequestParam(name = "limit", defaultValue = "255") Integer limit, @RequestParam(name = "page", defaultValue = "0") Integer page) {
        try {
            return ResponseFactory.getSuccessResponse(Response.SUCCESS, iTrademarkService.getListTrademark(limit, page));
        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

    @GetMapping("/get")
    public Response getTrademark(@RequestParam(name = "id" , required = true) Integer id) {
        try {
            return ResponseFactory.getSuccessResponse(Response.SUCCESS, iTrademarkService.getTrademark(id));
        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

    @GetMapping("/get-es")
    public Response getListTrademarkEs(@RequestParam(name = "limit", defaultValue = "255") Integer limit,
                                       @RequestParam(name = "page", defaultValue = "0") Integer page,
                                       @RequestParam(name = "keyword",required = false)String keyword) {
        try {
            return ResponseFactory.getSuccessResponse(Response.SUCCESS, iTrademarkService.getPage(limit, page , keyword));
        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }
}
