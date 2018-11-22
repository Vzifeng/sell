package com.example.sell.service.impl;

import com.example.sell.po.ProduceInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProduceInfoServiceImplTest {
    @Autowired
    private ProduceInfoServiceImpl produceInfoService;
    @Test
    public void getOne() {
        ProduceInfo produceInfo = produceInfoService.getOne("123456");
        Assert.assertEquals("123458",produceInfo.getProduceId());
    }

    @Test
    public void findUpAll() {
        List<ProduceInfo> list = produceInfoService.findUpAll();
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void findAll() {
        PageRequest request = new PageRequest(0,4);
        Page<ProduceInfo> result = produceInfoService.findAll(request);
        System.out.println(result.getTotalElements());
    }
    @Test
    public void save() {
        ProduceInfo produceInfo = new ProduceInfo();
        produceInfo.setProduceId("1234510");
        produceInfo.setProduceName("北京烤虾");
        produceInfo.setProducePrice(BigDecimal.valueOf(233.55));
        produceInfo.setProduceStock(100);
        produceInfo.setProduceDiscription("老贵了，但很好吃");
        produceInfo.setProduceIcon("http://xxxxxx.jpg");
        produceInfo.setProduceStatus(0);
        produceInfo.setCategoryType(3);
        ProduceInfo result = produceInfoService.save(produceInfo);
        Assert.assertNotNull(result);
    }
}