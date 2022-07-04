package com.huce.it.ecommerce.layer.application.domain.service;

import com.huce.it.ecommerce.layer.application.domain.entity.ProductGroup;
import com.huce.it.ecommerce.layer.application.domain.entity.ProductType;
import com.huce.it.ecommerce.layer.application.domain.model.dto.ProductTypeDto;
import com.huce.it.ecommerce.unitity.response.ResultResponse;

import java.io.IOException;
import java.util.List;

public interface IProductTypeService {

    ProductTypeDto createType(ProductTypeDto productTypeDto) throws IOException;
    ProductTypeDto changeActive(ProductTypeDto productTypeDto) throws Exception;
    ProductTypeDto updateProductType(ProductTypeDto productTypeDto) throws Exception;
    ResultResponse<ProductTypeDto> getListType(Integer limit, Integer page);
    ProductTypeDto getType(Integer id);

    ResultResponse getPage(Integer limit,Integer page ,String keyword);
    List<ProductType> getListType();

}
