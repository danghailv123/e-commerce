package com.huce.it.ecommerce.layer.presentation.controller;

import com.huce.it.ecommerce.layer.application.domain.model.dto.ProductGroupDto;
import com.huce.it.ecommerce.layer.application.domain.model.dto.ProductTypeDto;
import com.huce.it.ecommerce.layer.application.domain.service.IProductGroupService;
import com.huce.it.ecommerce.layer.application.domain.service.IProductTypeService;
import com.huce.it.ecommerce.unitity.response.Response;
import com.huce.it.ecommerce.unitity.response.ResponseFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("huce/product-type")
public class ProductTypeController {

    private final IProductTypeService iProductTypeService;

    public ProductTypeController(IProductTypeService iProductTypeService) {
        this.iProductTypeService = iProductTypeService;
    }

    @PostMapping("/create")
    public Response createProductType(@RequestBody ProductTypeDto productTypeDto) {
        try {
            return ResponseFactory.getSuccessResponse(Response.SUCCESS, iProductTypeService.createType(productTypeDto));
        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

    @PostMapping("/update")
    public Response updateProductType(@RequestBody ProductTypeDto productTypeDto) {
        try {
            return ResponseFactory.getSuccessResponse(Response.SUCCESS, iProductTypeService.updateProductType(productTypeDto));
        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

    @PostMapping("change-status")
    public Response changeStatusProductType(@RequestBody ProductTypeDto productTypeDto) {
        try {
            return ResponseFactory.getSuccessResponse(Response.SUCCESS, iProductTypeService.changeActive(productTypeDto));
        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

    @GetMapping("/get-list")
    public Response getListProductType(@RequestParam(name = "limit", defaultValue = "255") Integer limit, @RequestParam(name = "page", defaultValue = "0") Integer page) {
        try {
            return ResponseFactory.getSuccessResponse(Response.SUCCESS, iProductTypeService.getListType(limit, page));
        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

    @GetMapping("/get")
    public Response getProductGroup(@RequestParam(name = "id" , required = true) Integer id) {
        try {
            return ResponseFactory.getSuccessResponse(Response.SUCCESS, iProductTypeService.getType(id));
        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

}
