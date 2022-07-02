package com.huce.it.ecommerce.layer.presentation.controller;

import com.huce.it.ecommerce.layer.application.domain.model.dto.PromotionDto;
import com.huce.it.ecommerce.layer.application.domain.service.IPromotionService;
import com.huce.it.ecommerce.unitity.response.Response;
import com.huce.it.ecommerce.unitity.response.ResponseFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/huce/promotion")
public class PromotionController {
    private final IPromotionService iPromotionService;

    public PromotionController(IPromotionService iPromotionService) {
        this.iPromotionService = iPromotionService;
    }


    @PostMapping("create")
    public Response createPromotion(@RequestBody PromotionDto promotionDto){
        try {
            return ResponseFactory.getSuccessResponse(Response.SUCCESS,iPromotionService.create(promotionDto));
        }catch (Exception exception){
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

    @DeleteMapping("delete")
    public Response deletePromotion(@RequestParam(name = "id")Integer id){
        try {
            iPromotionService.delete(id);
            return ResponseFactory.getSuccessResponse(Response.SUCCESS);
        }catch (Exception exception){
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

    @PostMapping("update")
    public Response updatePromotion(@RequestBody PromotionDto promotionDto){
        try {
            return ResponseFactory.getSuccessResponse(Response.SUCCESS,iPromotionService.update(promotionDto));
        }catch (Exception exception){
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

    @GetMapping("get-list")
    public Response getPagePromotion(@RequestParam(name = "page", required = false, defaultValue ="0")Integer page,
                                    @RequestParam(name = "limit",required = false,defaultValue = "10")Integer limit){
        try {
            return ResponseFactory.getSuccessResponse(Response.SUCCESS, iPromotionService.getPage(page, limit));
        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }


    @GetMapping("/get")
    public Response getPromotion(@RequestParam(name = "id" , required = true) Integer id) {
        try {
            return ResponseFactory.getSuccessResponse(Response.SUCCESS, iPromotionService.getPromotion(id));
        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

}
