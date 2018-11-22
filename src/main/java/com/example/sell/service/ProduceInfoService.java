package com.example.sell.service;

import com.example.sell.dto.CartDto;
import com.example.sell.po.ProduceInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ProduceInfoService {
    //根据商品Id查询
    ProduceInfo getOne(String produceInfoId);
    //查询在架商品
    List<ProduceInfo> findUpAll();
    //查询全部商品
    Page<ProduceInfo> findAll(Pageable pageable);

    //保存与修改
    ProduceInfo save(ProduceInfo produceInfo);
    //加库存
    void increaseStock(List<CartDto> cartDtoList);
    //增库存
    void decreaseStock(List<CartDto> cartDtoList);
}
