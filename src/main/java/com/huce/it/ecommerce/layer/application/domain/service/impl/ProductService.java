package com.huce.it.ecommerce.layer.application.domain.service.impl;

import com.huce.it.ecommerce.config.Constants;
import com.huce.it.ecommerce.layer.application.domain.dao.*;
import com.huce.it.ecommerce.layer.application.domain.dao.elasticsearch.IElasticProductDao;
import com.huce.it.ecommerce.layer.application.domain.entity.Product;
import com.huce.it.ecommerce.layer.application.domain.entity.ProductGroup;
import com.huce.it.ecommerce.layer.application.domain.entity.ProductType;
import com.huce.it.ecommerce.layer.application.domain.model.dto.ProductDto;
import com.huce.it.ecommerce.layer.application.domain.model.dto.ProductGroupDto;
import com.huce.it.ecommerce.layer.application.domain.service.IProductService;
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
        productDto.setPromotionName( product.getPromId() != null ? iPromotionDao.getById(product.getPromId()).getName() : "");
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
        productDto.setPromotionName( product.getPromId() != null ? iPromotionDao.getById(product.getPromId()).getName() : "");
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

    @Override
    public ResultResponse<ProductDto> getListProduct(Integer limit, Integer page) {

        Pageable pageable = PageRequest.of(page, 255, Sort.by("id").descending());
        Page<Product> products = iProductDao.findAll(pageable);
        Long total = products.getTotalElements();
        List<ProductDto> productDtos = new ArrayList<>();
        products.get().forEach(product -> {productDtos.add(Constants.SERIALIZER.convertValue(product,ProductDto.class));});
        ResultResponse<ProductDto> resultResponse = new ResultResponse<>();
        resultResponse.setData(productDtos);
        resultResponse.setLimit(limit);
        resultResponse.setPage(page);
        resultResponse.setTotal(total);
        resultResponse.setTotalPage((int) (total / limit));
        return resultResponse;

    }

    @Override
    public List<Product> getListProduct() {
        return iProductDao.findAll();
    }

    @Override
    public ProductDto changeActive(ProductDto productDto) throws Exception {
        Product product = iProductDao.getById(productDto.getId());
        if (product==null){
            throw new Exception("product  not exist");
        }
        if (productDto.getStatus()!=null)
            product.setStatus(productDto.getStatus());
        ProductDto productDto1 = Constants.SERIALIZER.convertValue(product,ProductDto.class);
        ProductType productType = iProductTypeDao.getById(product.getPtId());
        productDto1.setGroupName(iProductGroupDao.getNameById(productType.getProductGroupId()));
        productDto1.setNameType(productType.getName());
        productDto1.setPromotionName( product.getPromId() != null ? iPromotionDao.getById(product.getPromId()).getName() : "");
        productDto1.setTrademarkName(iTrademarkDao.getById(product.getTraId()).getName());
        iElasticProductDao.save(productDto1);
        return Constants.SERIALIZER.convertValue(iProductDao.save(product),ProductDto.class);
    }
}
