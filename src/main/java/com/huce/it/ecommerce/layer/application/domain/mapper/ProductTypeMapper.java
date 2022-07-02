package com.huce.it.ecommerce.layer.application.domain.mapper;

import com.huce.it.ecommerce.layer.application.domain.entity.ProductGroup;
import com.huce.it.ecommerce.layer.application.domain.entity.ProductType;
import com.huce.it.ecommerce.layer.application.domain.model.dto.ProductGroupDto;
import com.huce.it.ecommerce.layer.application.domain.model.dto.ProductTypeDto;
import com.huce.it.ecommerce.layer.application.domain.service.IProductGroupService;
import org.springframework.stereotype.Component;

@Component
public class ProductTypeMapper {

    private final IProductGroupService iProductGroupService;


    public ProductTypeMapper(IProductGroupService iProductGroupService) {
        this.iProductGroupService = iProductGroupService;
    }

    public ProductTypeDto mapperProductTypeToProductTypeDto(ProductType from){
        ProductTypeDto to = new ProductTypeDto();

        to.setId(from.getId());
        to.setPg_id(from.getProductGroupId());

        ProductGroupDto productGroup = iProductGroupService.getGroup(from.getProductGroupId());
        to.setPg_name(productGroup.getName());

        to.setName(from.getName());
        to.setDescription(from.getDescription());
        to.setStatus(from.getStatus());
        return to ;
    }
}
