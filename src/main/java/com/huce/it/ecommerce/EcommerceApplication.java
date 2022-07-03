package com.huce.it.ecommerce;

import com.huce.it.ecommerce.config.Constants;
import com.huce.it.ecommerce.layer.application.domain.dao.elasticsearch.IElasticProductGroupDao;
import com.huce.it.ecommerce.layer.application.domain.entity.ProductGroup;
import com.huce.it.ecommerce.layer.application.domain.model.dto.ProductGroupDto;
import com.huce.it.ecommerce.layer.application.domain.service.IAccountService;
import com.huce.it.ecommerce.layer.application.domain.service.impl.ProductGroupService;
import com.huce.it.ecommerce.layer.application.domain.service.impl.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner {
    private final IAccountService iAccountService;
    private final IElasticProductGroupDao iElasticProductGroupDao;
    private final ProductGroupService productGroupService;



    public EcommerceApplication(IAccountService iAccountService,
                                IElasticProductGroupDao iElasticProductGroupDao,
                                ProductGroupService productGroupService) {
        this.iAccountService = iAccountService;
        this.iElasticProductGroupDao = iElasticProductGroupDao;
        this.productGroupService = productGroupService;
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

        productGroupService.getListGroup().forEach(productGroup -> {
            ProductGroupDto productGroupDto = Constants.SERIALIZER.convertValue(productGroup,ProductGroupDto.class);
            try {
                iElasticProductGroupDao.save(productGroupDto);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }


}
