package com.huce.it.ecommerce.layer.application.domain.service.impl;

import com.huce.it.ecommerce.layer.application.domain.dao.IProductDao;
import com.huce.it.ecommerce.layer.application.domain.entity.Product;
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

    public ProductService(IProductDao iProductDao) {
        this.iProductDao = iProductDao;
    }

    @Override
    public Product createProduct(Product product) {
        return iProductDao.save(product);
    }

    @Override
    public void deleteProduct(Integer productId) {
        iProductDao.deleteById(productId);
    }

    @Override
    public Product updateProduct(Product product) throws IOException {
        if (iProductDao.existsById(product.getId())) throw new IOException("Product not exist!!!");
        return iProductDao.save(product);
    }

    @Override
    public Product getProduct(Integer productId) {
        return iProductDao.getById(productId);
    }

    @Override
    public ResultResponse getPage(Integer limit, Integer page, String keyword) {
        return null;
    }
}
