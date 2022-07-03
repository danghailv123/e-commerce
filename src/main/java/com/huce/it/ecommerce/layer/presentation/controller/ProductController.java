package com.huce.it.ecommerce.layer.presentation.controller;

import com.huce.it.ecommerce.layer.application.domain.entity.Product;
import com.huce.it.ecommerce.layer.application.domain.model.dto.ProductDto;
import com.huce.it.ecommerce.layer.application.domain.service.IProductService;
import com.huce.it.ecommerce.unitity.response.Response;
import com.huce.it.ecommerce.unitity.response.ResponseFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/huce/product")
public class ProductController {

    private final IProductService iProductService;

    public ProductController(IProductService iProductService) {
        this.iProductService = iProductService;
    }

    @PostMapping("create")
    public Response createProduct(Product product){
        try {
            return ResponseFactory.getSuccessResponse(Response.SUCCESS,iProductService.createProduct(product));
        }catch (Exception  exception){
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

    @GetMapping("get")
    public Response getProduct(@RequestParam(name = "id")Integer id){
        try {
            return ResponseFactory.getSuccessResponse(Response.SUCCESS,iProductService.getProduct(id));
        }catch (Exception  exception){
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

    @GetMapping("get-list")
    public Response getListProduct(@RequestParam(name = "limit", defaultValue = "10", required = false) Integer limit,
                                   @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
                                   @RequestParam(name = "keyword",required = false)String keyword){
        try {
            return ResponseFactory.getSuccessResponse(Response.SUCCESS,iProductService.getPage(limit,page,keyword));
        }catch (Exception  exception){
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }

}
