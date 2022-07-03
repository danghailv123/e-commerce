package com.huce.it.ecommerce.layer.application.domain.service.impl;

import com.huce.it.ecommerce.config.Constants;
import com.huce.it.ecommerce.layer.application.domain.dao.IProductGroupDao;
import com.huce.it.ecommerce.layer.application.domain.dao.elasticsearch.IElasticProductGroupDao;
import com.huce.it.ecommerce.layer.application.domain.entity.Product;
import com.huce.it.ecommerce.layer.application.domain.entity.ProductGroup;
import com.huce.it.ecommerce.layer.application.domain.model.dto.ProductGroupDto;
import com.huce.it.ecommerce.layer.application.domain.service.IProductGroupService;
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
public class ProductGroupService implements IProductGroupService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final IProductGroupDao iProductGroupDao;
    private final IElasticProductGroupDao iElasticProductGroupDao;

    public ProductGroupService(IProductGroupDao iProductGroupDao, IElasticProductGroupDao iElasticProductGroupDao) {
        this.iProductGroupDao = iProductGroupDao;
        this.iElasticProductGroupDao = iElasticProductGroupDao;
    }

    @Override
    public ProductGroupDto createGroup(ProductGroupDto productGroupDto) throws IOException {
        ProductGroup productGroup = Constants.SERIALIZER.convertValue(productGroupDto,ProductGroup.class);

        ProductGroup ProductGroup = iProductGroupDao.save(productGroup);
        productGroupDto.setId(productGroup.getId());
        iElasticProductGroupDao.save(productGroupDto);
        return Constants.SERIALIZER.convertValue(ProductGroup,ProductGroupDto.class);
    }

    @Override
    public ProductGroupDto changeActive(ProductGroupDto productGroupDto) throws Exception {
        ProductGroup productGroup = iProductGroupDao.getById(productGroupDto.getId());
        if (productGroup==null){
            throw new Exception("product group not exist");
        }
        if (productGroupDto.getStatus()!=null)
            productGroup.setStatus(productGroupDto.getStatus());
        iElasticProductGroupDao.save(Constants.SERIALIZER.convertValue(productGroup,ProductGroupDto.class));
        return Constants.SERIALIZER.convertValue(iProductGroupDao.save(productGroup),ProductGroupDto.class);
    }

    @Override
    public ProductGroupDto updateProductGroup(ProductGroupDto productGroupDto) throws Exception {
        ProductGroup productGroup = iProductGroupDao.getById(productGroupDto.getId());
        if (productGroup==null){
            throw new Exception("product group not exist");
        }
        if (productGroupDto.getName()!=null)
            productGroup.setName(productGroupDto.getName());
        iElasticProductGroupDao.save(iElasticProductGroupDao.save(Constants.SERIALIZER.convertValue(productGroup,ProductGroupDto.class)));
        return Constants.SERIALIZER.convertValue(iProductGroupDao.save(productGroup),ProductGroupDto.class);
    }

    @Override
    public ResultResponse<ProductGroupDto> getListGroup(Integer limit, Integer page) {
        Pageable pageable = PageRequest.of(page, 255, Sort.by("id").descending());
        Page<ProductGroup> productGroups = iProductGroupDao.findAll(pageable);
        Long total = productGroups.getTotalElements();
        List<ProductGroupDto> productGroupDtos = new ArrayList<>();
        productGroups.get().forEach(productGroup -> {productGroupDtos.add(Constants.SERIALIZER.convertValue(productGroup,ProductGroupDto.class));});
        ResultResponse<ProductGroupDto> resultResponse = new ResultResponse<>();
        resultResponse.setData(productGroupDtos);
        resultResponse.setLimit(limit);
        resultResponse.setPage(page);
        resultResponse.setTotal(total);
        resultResponse.setTotalPage((int) (total / limit));
        return resultResponse;
    }

    @Override
    public ProductGroupDto getGroup(Integer id) {
        return Constants.SERIALIZER.convertValue(iProductGroupDao.getById(id),ProductGroupDto.class);
    }

    @Override
    public ResultResponse getPage(Integer limit, Integer page, String keyword) {
        return iElasticProductGroupDao.search(keyword, limit, page);
    }

    @Override
    public List<ProductGroup> getListGroup() {
        return iProductGroupDao.findAll();
    }
}
