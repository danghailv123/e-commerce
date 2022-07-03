package com.huce.it.ecommerce.layer.application.domain.service;

import com.huce.it.ecommerce.layer.application.domain.entity.ProductGroup;
import com.huce.it.ecommerce.layer.application.domain.model.dto.ProductGroupDto;
import com.huce.it.ecommerce.unitity.response.ResultResponse;

import java.io.IOException;
import java.util.List;

public interface IProductGroupService {
    ProductGroupDto createGroup(ProductGroupDto productGroupDto) throws IOException;
    ProductGroupDto changeActive(ProductGroupDto productGroupDto) throws Exception;
    ProductGroupDto updateProductGroup(ProductGroupDto productGroupDto) throws Exception;
    ResultResponse<ProductGroupDto> getListGroup(Integer limit,Integer page);
    ProductGroupDto getGroup(Integer id);

    ResultResponse getPage(Integer limit,Integer page ,String keyword);

    List<ProductGroup> getListGroup();
}
