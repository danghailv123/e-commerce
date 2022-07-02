package com.huce.it.ecommerce.layer.presentation.controller;

import com.huce.it.ecommerce.layer.application.domain.model.dto.ProductGroupDto;
import com.huce.it.ecommerce.layer.application.domain.service.IProductGroupService;
import com.huce.it.ecommerce.unitity.response.Response;
import com.huce.it.ecommerce.unitity.response.ResponseFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("huce/product-group")
public class ProductGroupController {
    private final IProductGroupService iProductGroupService;

    public ProductGroupController(IProductGroupService iProductGroupService) {
        this.iProductGroupService = iProductGroupService;
    }

    @PostMapping("/create")
    public Response createProductGroup(@RequestBody ProductGroupDto productGroupDto) {
        try {
            return ResponseFactory.getSuccessResponse(Response.SUCCESS, iProductGroupService.createGroup(productGroupDto));
        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

    @PostMapping("/update")
    public Response updateProductGroup(@RequestBody ProductGroupDto productGroupDto) {
        try {
            return ResponseFactory.getSuccessResponse(Response.SUCCESS, iProductGroupService.updateProductGroup(productGroupDto));
        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

    @PostMapping("change-status")
    public Response changeStatusProductGroup(@RequestBody ProductGroupDto productGroupDto) {
        try {
            return ResponseFactory.getSuccessResponse(Response.SUCCESS, iProductGroupService.changeActive(productGroupDto));
        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

    @GetMapping("/get-list")
    public Response getListProductGroup(@RequestParam(name = "limit", defaultValue = "30") Integer limit, @RequestParam(name = "page", defaultValue = "0") Integer page) {
        try {
            return ResponseFactory.getSuccessResponse(Response.SUCCESS, iProductGroupService.getListGroup(limit, page));
        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

    @GetMapping("/get")
    public Response getProductGroup(@RequestParam(name = "id" , required = true) Integer id) {
        try {
            return ResponseFactory.getSuccessResponse(Response.SUCCESS, iProductGroupService.getGroup(id));
        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }
}
