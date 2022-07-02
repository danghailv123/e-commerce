package com.huce.it.ecommerce.layer.application.domain.service;

import com.huce.it.ecommerce.layer.application.domain.model.dto.ProductTypeDto;
import com.huce.it.ecommerce.unitity.response.ResultResponse;

public interface IProductTypeService {

    ProductTypeDto createType(ProductTypeDto productTypeDto);
    ProductTypeDto changeActive(ProductTypeDto productTypeDto) throws Exception;
    ProductTypeDto updateProductType(ProductTypeDto productTypeDto) throws Exception;
    ResultResponse<ProductTypeDto> getListType(Integer limit, Integer page);
    ProductTypeDto getType(Integer id);

}
