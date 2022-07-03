package com.huce.it.ecommerce.layer.application.domain.service.impl;

import com.huce.it.ecommerce.config.Constants;
import com.huce.it.ecommerce.layer.application.domain.dao.*;
import com.huce.it.ecommerce.layer.application.domain.dao.elasticsearch.IElasticProductDao;
import com.huce.it.ecommerce.layer.application.domain.entity.Product;
import com.huce.it.ecommerce.layer.application.domain.entity.ProductType;
import com.huce.it.ecommerce.layer.application.domain.model.dto.ProductDto;
import com.huce.it.ecommerce.layer.application.domain.service.IProductService;
import com.huce.it.ecommerce.unitity.response.ResultResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ProductService implements IProductService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private final IProductDao iProductDao;
    private final IProductGroupDao iProductGroupDao;
    private final IProductTypeDao iProductTypeDao;
    private final IPromotionDao iPromotionDao;
    private final ITrademarkDao iTrademarkDao;

    private final IElasticProductDao iElasticProductDao;

    public ProductService(IProductDao iProductDao, IProductGroupDao iProductGroupDao, IProductTypeDao iProductTypeDao, IPromotionDao iPromotionDao, ITrademarkDao iTrademarkDao, IElasticProductDao iElasticProductDao) {
        this.iProductDao = iProductDao;
        this.iProductGroupDao = iProductGroupDao;
        this.iProductTypeDao = iProductTypeDao;
        this.iPromotionDao = iPromotionDao;
        this.iTrademarkDao = iTrademarkDao;
        this.iElasticProductDao = iElasticProductDao;
    }

    @Override
    public ProductDto createProduct(Product productInput) throws IOException {
        Product product = iProductDao.save(productInput);
        ProductDto productDto = Constants.SERIALIZER.convertValue(product, ProductDto.class);
        ProductType productType = iProductTypeDao.getById(product.getPtId());
        productDto.setGroupName(iProductGroupDao.getNameById(productType.getProductGroupId()));
        productDto.setNameType(productType.getName());
        productDto.setPromotionName(iPromotionDao.getById(product.getPromId()).getName());
        productDto.setTrademarkName(iTrademarkDao.getById(product.getTraId()).getName());
        iElasticProductDao.save(productDto);
        return productDto;
    }

    @Override
    public void deleteProduct(Integer productId) {
        iProductDao.deleteById(productId);
    }

    @Override
    public Product updateProduct(Product productInput) throws IOException {
        if (iProductDao.existsById(productInput.getId())) throw new IOException("Product not exist!!!");
        Product product = iProductDao.save(productInput);
        ProductDto productDto = Constants.SERIALIZER.convertValue(product, ProductDto.class);
        ProductType productType = iProductTypeDao.getById(product.getPtId());
        productDto.setGroupName(iProductGroupDao.getNameById(productType.getProductGroupId()));
        productDto.setNameType(productType.getName());
        productDto.setPromotionName(iPromotionDao.getById(product.getPromId()).getName());
        productDto.setTrademarkName(iTrademarkDao.getById(product.getTraId()).getName());
        iElasticProductDao.save(productDto);
        return product;
    }

    @Override
    public Product getProduct(Integer productId) {
        return iProductDao.getById(productId);
    }

    @Override
    public ResultResponse getPage(Integer limit, Integer page, String keyword) {

        return iElasticProductDao.search(keyword, limit, page);
    }
}
