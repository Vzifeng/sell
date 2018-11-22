package com.example.sell.dao;

import com.example.sell.po.ProduceInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProduceInfoDaoTest {
    @Autowired
    private ProduceInfoDao produceInfoDao;
    @Test
    public void saveTest(){
        ProduceInfo produceInfo = new ProduceInfo();
        produceInfo.setProduceId("123456");
        produceInfo.setProduceName("北京烤鸭");
        produceInfo.setProducePrice(BigDecimal.valueOf(233.55));
        produceInfo.setProduceStock(100);
        produceInfo.setProduceDiscription("老贵了，但很好吃");
        produceInfo.setProduceIcon("http://xxxxxx.jpg");
        produceInfo.setProduceStatus(0);
        produceInfo.setCategoryType(3);
        ProduceInfo result = produceInfoDao.save(produceInfo);
        Assert.assertNotNull(result);

    }
    @Test
    public void findByProduceStatus() {
        List<ProduceInfo> result = produceInfoDao.findByProduceStatus(0);
        Assert.assertNotEquals(0,result.size());
    }
}
