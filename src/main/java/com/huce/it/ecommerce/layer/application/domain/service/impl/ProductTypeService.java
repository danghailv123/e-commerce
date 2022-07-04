package com.huce.it.ecommerce.layer.application.domain.service.impl;

import com.huce.it.ecommerce.config.Constants;
import com.huce.it.ecommerce.layer.application.domain.dao.IProductTypeDao;
import com.huce.it.ecommerce.layer.application.domain.dao.elasticsearch.IElasticProductTypeDao;
import com.huce.it.ecommerce.layer.application.domain.entity.ProductGroup;
import com.huce.it.ecommerce.layer.application.domain.entity.ProductType;
import com.huce.it.ecommerce.layer.application.domain.mapper.ProductTypeMapper;
import com.huce.it.ecommerce.layer.application.domain.model.dto.ProductTypeDto;
import com.huce.it.ecommerce.layer.application.domain.service.IProductTypeService;
import com.huce.it.ecommerce.unitity.response.ResultResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductTypeService implements IProductTypeService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final IProductTypeDao iProductTypeDao;
    private final ProductTypeMapper productTypeMapper;

    private final IElasticProductTypeDao iElasticProductTypeDao;

    public ProductTypeService(IProductTypeDao iProductTypeDao, ProductTypeMapper productTypeMapper, IElasticProductTypeDao iElasticProductTypeDao) {
        this.iProductTypeDao = iProductTypeDao;
        this.productTypeMapper = productTypeMapper;
        this.iElasticProductTypeDao = iElasticProductTypeDao;
    }


    @Override
    public ProductTypeDto createType(ProductTypeDto productTypeDto) throws IOException {
        ProductType productType = Constants.SERIALIZER.convertValue(productTypeDto,ProductType.class);
        ProductType productType1 = iProductTypeDao.save(productType);
        ProductTypeDto productTypeEs = productTypeMapper.mapperProductTypeToProductTypeDto(productType);
        iElasticProductTypeDao.save(productTypeEs);
        return Constants.SERIALIZER.convertValue(productType1, ProductTypeDto.class);
    }

    @Override
    public ProductTypeDto changeActive(ProductTypeDto productTypeDto) throws Exception {
        ProductType productType = iProductTypeDao.getById(productTypeDto.getId());
        if (productType==null){
            throw new Exception("product type not exist");
        }
        if (productType.getStatus()!=null){
            productType.setStatus(productTypeDto.getStatus());

            ProductTypeDto productTypeDtoEs = productTypeMapper.mapperProductTypeToProductTypeDto(productType);
            iElasticProductTypeDao.save(productTypeDtoEs);
        }
        return Constants.SERIALIZER.convertValue(iProductTypeDao.save(productType),ProductTypeDto.class);
    }

    @Override
    public ProductTypeDto updateProductType(ProductTypeDto productTypeDto) throws Exception {
        ProductType productType = iProductTypeDao.getById(productTypeDto.getId());
        if (productType==null){
            throw new Exception("product Type not exist");
        }
        if (productType.getName()!=null){
            productType.setName(productTypeDto.getName());
            productType.setProductGroupId(productTypeDto.getPg_id());
            ProductTypeDto productTypeEs = productTypeMapper.mapperProductTypeToProductTypeDto(productType);
            iElasticProductTypeDao.save(productTypeEs);
        }
        return Constants.SERIALIZER.convertValue(iProductTypeDao.save(productType),ProductTypeDto.class);
    }

    @Override
    public ResultResponse<ProductTypeDto> getListType(Integer limit, Integer page) {
        Pageable pageable = PageRequest.of(page, 255, Sort.by("id").descending());
        Page<ProductType> productTypes = iProductTypeDao.findAll(pageable);
        Long total = productTypes.getTotalElements();
        List<ProductTypeDto> productTypeDtos = new ArrayList<>();
        productTypes.get().forEach(productType -> {
            productTypeDtos.add(productTypeMapper.mapperProductTypeToProductTypeDto(productType));
        });
        ResultResponse<ProductTypeDto> resultResponse = new ResultResponse<>();
        resultResponse.setData(productTypeDtos);
        resultResponse.setLimit(limit);
        resultResponse.setPage(page);
        resultResponse.setTotal(total);
        resultResponse.setTotalPage((int) (total / limit));
        return resultResponse;
    }

    @Override
    public ProductTypeDto getType(Integer id) {
        return productTypeMapper.mapperProductTypeToProductTypeDto(iProductTypeDao.getById(id)) ;
    }

    @Override
    public ResultResponse getPage(Integer limit, Integer page, String keyword) {
        return iElasticProductTypeDao.search(keyword , limit , page);
    }

    @Override
    public List<ProductType> getListType() {
        return iProductTypeDao.findAll();
    }
}
