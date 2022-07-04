package com.huce.it.ecommerce;

import com.huce.it.ecommerce.config.Constants;
import com.huce.it.ecommerce.layer.application.domain.dao.IProductGroupDao;
import com.huce.it.ecommerce.layer.application.domain.dao.IProductTypeDao;
import com.huce.it.ecommerce.layer.application.domain.dao.IPromotionDao;
import com.huce.it.ecommerce.layer.application.domain.dao.ITrademarkDao;
import com.huce.it.ecommerce.layer.application.domain.dao.elasticsearch.*;
import com.huce.it.ecommerce.layer.application.domain.entity.ProductType;
import com.huce.it.ecommerce.layer.application.domain.mapper.ProductTypeMapper;
import com.huce.it.ecommerce.layer.application.domain.model.dto.*;
import com.huce.it.ecommerce.layer.application.domain.service.IAccountService;
import com.huce.it.ecommerce.layer.application.domain.service.impl.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner {
    private final IAccountService iAccountService;
    private final IElasticProductGroupDao iElasticProductGroupDao;
    private final IElasticProductTypeDao iElasticProductTypeDao;
    private final IElasticTrademarkDao iElasticTrademarkDao;
    private final IElasticUserDao iElasticUserDao;
    private final IElasticProductDao iElasticProductDao;
    private final IProductTypeDao iProductTypeDao;
    private final IProductGroupDao iProductGroupDao;
    private final IPromotionDao iPromotionDao;
    private final ITrademarkDao iTrademarkDao;
    private final ProductGroupService productGroupService;
    private final ProductTypeService productTypeService;
    private final ProductTypeMapper productTypeMapper;
    private final TrademarkService trademarkService;
    private final AccountService accountService;
    private final ProductService productService;



    public EcommerceApplication(IAccountService iAccountService,
                                IElasticProductGroupDao iElasticProductGroupDao,
                                IElasticProductTypeDao iElasticProductTypeDao,
                                IElasticTrademarkDao iElasticTrademarkDao, IElasticUserDao iElasticUserDao, IElasticProductDao iElasticProductDao, IProductTypeDao iProductTypeDao, IProductGroupDao iProductGroupDao, IPromotionDao iPromotionDao, ITrademarkDao iTrademarkDao, ProductGroupService productGroupService,
                                ProductTypeService productTypeService,
                                ProductTypeMapper productTypeMapper, TrademarkService trademarkService, AccountService accountService, ProductService productService) {
        this.iAccountService = iAccountService;
        this.iElasticProductGroupDao = iElasticProductGroupDao;
        this.iElasticProductTypeDao = iElasticProductTypeDao;
        this.iElasticTrademarkDao = iElasticTrademarkDao;
        this.iElasticUserDao = iElasticUserDao;
        this.iElasticProductDao = iElasticProductDao;
        this.iProductTypeDao = iProductTypeDao;
        this.iProductGroupDao = iProductGroupDao;
        this.iPromotionDao = iPromotionDao;
        this.iTrademarkDao = iTrademarkDao;
        this.productGroupService = productGroupService;
        this.productTypeService = productTypeService;
        this.productTypeMapper = productTypeMapper;
        this.trademarkService = trademarkService;
        this.accountService = accountService;
        this.productService = productService;
    }

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        if (iAccountService.getAccountByEmail("admin@gmail.com") == null) {
//            AccountDto account = new AccountDto();
//            account.setName("admin");
//            account.setCreateDate(new Timestamp(System.currentTimeMillis()));
//            account.setPassword("123456");
//            account.setRole("admin");
//            account.setPhoneNumber("1 k");
//            account.setStatus(1);
//            account.setEmail("admin@gmail.com");
//            iAccountService.createAccount(account);
//        }

        reindexProductGroup();

       reindexProductType();

        reindexTrademark();

        reindexAccount();

        reindexProduct();

    }

    private void reindexProductGroup(){

        productGroupService.getListGroup().forEach(productGroup -> {
            ProductGroupDto productGroupDto = Constants.SERIALIZER.convertValue(productGroup,ProductGroupDto.class);
            try {
                iElasticProductGroupDao.save(productGroupDto);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void reindexProductType(){

        productTypeService.getListType().forEach(productType -> {
            ProductTypeDto productTypeDto = productTypeMapper.mapperProductTypeToProductTypeDto(productType);
            try {
                iElasticProductTypeDao.save(productTypeDto);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void reindexTrademark(){

        trademarkService.getLisTrademark().forEach(trademark -> {
            TrademarkDto trademarkDto = Constants.SERIALIZER.convertValue(trademark,TrademarkDto.class);
            try {
                iElasticTrademarkDao.save(trademarkDto);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void reindexAccount(){

        accountService.getListAccount().forEach(account -> {
            AccountDto accountDto = Constants.SERIALIZER.convertValue(account,AccountDto.class);
            accountDto.setUserId(account.getUserId());
            try {
                iElasticUserDao.save(accountDto);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void reindexProduct(){

        productService.getListProduct().forEach(product -> {
            ProductDto productDto = Constants.SERIALIZER.convertValue(product,ProductDto.class);
            ProductType productType = iProductTypeDao.getById(product.getPtId());
            productDto.setGroupName(iProductGroupDao.getNameById(productType.getProductGroupId()));
            productDto.setNameType(productType.getName());
            productDto.setPromotionName( product.getPromId() != null ? iPromotionDao.getById(product.getPromId()).getName() : "");
            productDto.setTrademarkName(iTrademarkDao.getById(product.getTraId()).getName());
            try {
                iElasticProductDao.save(productDto);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
