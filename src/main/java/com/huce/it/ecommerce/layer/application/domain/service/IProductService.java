package com.huce.it.ecommerce.layer.application.domain.service;

import com.huce.it.ecommerce.layer.application.domain.entity.Product;
import com.huce.it.ecommerce.layer.application.domain.model.dto.ProductDto;
import com.huce.it.ecommerce.unitity.response.ResultResponse;

import java.io.IOException;

public interface IProductService {
    ProductDto createProduct(Product product) throws IOException;
    void deleteProduct(Integer productId);
    Product updateProduct(Product product) throws IOException;
    Product getProduct(Integer productId);

    ResultResponse getPage(Integer limit,Integer page ,String keyword);
}
