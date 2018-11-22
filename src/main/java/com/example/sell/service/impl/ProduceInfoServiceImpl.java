package com.example.sell.service.impl;

import com.example.sell.dao.ProduceInfoDao;
import com.example.sell.dto.CartDto;
import com.example.sell.eunms.ProduceStatusEnums;
import com.example.sell.eunms.ResultEnums;
import com.example.sell.exception.SellException;
import com.example.sell.po.ProduceInfo;
import com.example.sell.service.ProduceInfoService;
import com.example.sell.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class ProduceInfoServiceImpl implements ProduceInfoService {
    @Autowired
    private ProduceInfoDao produceInfoDao;

    @Override
    public ProduceInfo getOne(String produceInfoId) {
        return produceInfoDao.getOne(produceInfoId);
    }

    /**
     * 查询上架商品
     * @return
     */
    @Override
    public List <ProduceInfo> findUpAll() {
        return produceInfoDao.findByProduceStatus(ProduceStatusEnums.UP.getCode());
    }

    /**
     * 分页查询
     * @param pageable
     * @return
     */
    @Override
    public Page<ProduceInfo> findAll(Pageable pageable) {
        return produceInfoDao.findAll(pageable);
    }

    /**
     * 修改、新增
     * @param produceInfo
     * @return
     */
    @Override
    public ProduceInfo save(ProduceInfo produceInfo) {
        String id = UUIDUtil.getUUID();
        produceInfo.setProduceId(id);
        return produceInfoDao.save(produceInfo);
    }

    @Override
    public void increaseStock(List <CartDto> cartDtoList) {
        for (CartDto cartDto:cartDtoList) {
            ProduceInfo produceInfo = produceInfoDao.getOne(cartDto.getProduceId());
            if (produceInfo == null){
                throw new SellException(ResultEnums.PRODUCE_NOT_EXIST);
            }
            Integer aa = produceInfo.getProduceStock() + cartDto.getProduceQuantity();
            produceInfo.setProduceStock(aa);
            produceInfoDao.save(produceInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List <CartDto> cartDtoList) {
        for (CartDto cartDto:cartDtoList) {
            ProduceInfo produceInfo = produceInfoDao.getOne(cartDto.getProduceId());
            if (produceInfo == null){
                throw new SellException(ResultEnums.PRODUCE_NOT_EXIST);
            }
            Integer aa = produceInfo.getProduceStock() - cartDto.getProduceQuantity();
            if(aa < 0){
                throw new SellException(ResultEnums.PRODUCE_NOT_ENOUGH);
            }
            produceInfo.setProduceStock(aa);
            produceInfoDao.save(produceInfo);
        }
    }
}
