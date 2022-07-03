package com.huce.it.ecommerce.layer.application.domain.service.impl;

import com.huce.it.ecommerce.config.Constants;
import com.huce.it.ecommerce.layer.application.domain.dao.IReceiptDao;
import com.huce.it.ecommerce.layer.application.domain.entity.Receipt;
import com.huce.it.ecommerce.layer.application.domain.mapper.ReceiptMapper;
import com.huce.it.ecommerce.layer.application.domain.model.dto.ReceiptDto;
import com.huce.it.ecommerce.layer.application.domain.service.IReceiptService;
import com.huce.it.ecommerce.unitity.response.ResultResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReceiptService implements IReceiptService {
    private final IReceiptDao iReceiptDao;
    private final ReceiptMapper receiptMapper;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public ReceiptService(IReceiptDao iReceiptDao, ReceiptMapper receiptMapper) {
        this.iReceiptDao = iReceiptDao;
        this.receiptMapper = receiptMapper;
    }

    @Override
    public ReceiptDto create(ReceiptDto receiptDto) {
        Receipt receipt = Constants.SERIALIZER.convertValue(receiptDto,Receipt.class);
        logger.info("create receipt "+ receipt);
        return Constants.SERIALIZER.convertValue(iReceiptDao.save(receipt),ReceiptDto.class);
    }

    @Override
    public void delete(Integer id) throws Exception {
        Receipt receipt = iReceiptDao.getById(id);
        if (receipt==null) throw new Exception("Promotion not exist database");
        iReceiptDao.delete(receipt);
        logger.info("delete receipt id= {}",receipt.getId());
    }

    @Override
    public ReceiptDto update(ReceiptDto receiptDto) throws Exception {
        Receipt receipt = iReceiptDao.getById(receiptDto.getId());
        if (receipt==null) throw new Exception("Promotion not exist database");
        if (receipt.getStatus()!= null) receipt.setStatus(receiptDto.getStatus());
        if (receipt.getAddress()!= null) receipt.setAddress(receiptDto.getAddress());
        if (receipt.getCreateDate()!= null) receipt.setCreateDate(receiptDto.getCreateDate());
        if (receipt.getName()!= null) receipt.setName(receiptDto.getName());
        if (receipt.getPhoneNumber()!= null) receipt.setPhoneNumber(receiptDto.getPhoneNumber());
        if (receipt.getUserId()!= null) receipt.setUserId(receiptDto.getUserId());
        return Constants.SERIALIZER.convertValue(iReceiptDao.save(receipt), ReceiptDto.class);
    }

    @Override
    public ResultResponse<ReceiptDto> getPage(Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page,limit, Sort.by(Sort.Direction.DESC,"createDate"));
        Page<Receipt> receiptPage = iReceiptDao.findAll(pageable);
        List<ReceiptDto> receiptDtos = new ArrayList<>();
        receiptPage.getContent().forEach(receipt -> {
            receiptDtos.add(receiptMapper.mapReceiptToReceiptDto(receipt));
        });
        ResultResponse<ReceiptDto> resultResponse = new ResultResponse<>();
        resultResponse.setPage(page);
        resultResponse.setData(receiptDtos);
        resultResponse.setLimit(limit);
        resultResponse.setTotal(receiptPage.getTotalElements());
        return resultResponse;
    }
}
