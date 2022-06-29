package com.huce.it.ecommerce.layer.application.domain.service;

import com.huce.it.ecommerce.layer.application.domain.entity.ProductGroup;
import com.huce.it.ecommerce.layer.application.domain.model.dto.ProductGroupDto;
import com.huce.it.ecommerce.unitity.response.ResultResponse;

public interface IProductGroupService {
    ProductGroupDto createGroup(ProductGroupDto productGroupDto);
    ProductGroupDto changeActive(ProductGroupDto productGroupDto) throws Exception;
    ProductGroupDto updateProductGroup(ProductGroupDto productGroupDto) throws Exception;
    ResultResponse<ProductGroupDto> getListGroup(Integer limit,Integer page);
}
