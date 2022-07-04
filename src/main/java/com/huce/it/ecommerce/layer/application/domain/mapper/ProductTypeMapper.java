package com.huce.it.ecommerce.layer.application.domain.mapper;

import com.huce.it.ecommerce.layer.application.domain.dao.IProductGroupDao;
import com.huce.it.ecommerce.layer.application.domain.entity.ProductGroup;
import com.huce.it.ecommerce.layer.application.domain.entity.ProductType;
import com.huce.it.ecommerce.layer.application.domain.model.dto.ProductTypeDto;
import org.springframework.stereotype.Component;

@Component
public class ProductTypeMapper {

    private final IProductGroupDao iProductGroupDao;


    public ProductTypeMapper( IProductGroupDao iProductGroupDao) {
        this.iProductGroupDao = iProductGroupDao;
    }

    public ProductTypeDto mapperProductTypeToProductTypeDto(ProductType from){
        ProductTypeDto to = new ProductTypeDto();

        to.setId(from.getId());
        to.setPg_id(from.getProductGroupId());

        ProductGroup productGroup = iProductGroupDao.getById(from.getProductGroupId());
        to.setPg_name(productGroup.getName());

        to.setName(from.getName());
        to.setDescription(from.getDescription());
        to.setStatus(from.getStatus());
        return to ;
    }
}
