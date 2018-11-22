package com.example.sell.dao;

import com.example.sell.po.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {
    @Autowired
    OrderMasterDao orderMasterDao;
    @Test
    public void findByBuyerOpenid() throws Exception {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<OrderMaster> orderMasterPage = orderMasterDao.findByBuyerOpenid("111111",pageRequest);
        System.out.println(orderMasterPage.getTotalElements());
        Assert.assertNotEquals(0,orderMasterPage.getTotalElements());
    }
    @Test
    public void save() throws Exception{
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123457");
        orderMaster.setBuyerName("小李");
        orderMaster.setBuyerAddress("公司");
        orderMaster.setBuyerPhone("13623456987");
        orderMaster.setBuyerOpenid("111111");
        orderMaster.setOrderAmount(new BigDecimal(123.00));
        OrderMaster orderMaster1 = orderMasterDao.save(orderMaster);
        Assert.assertNotNull(orderMaster1);
    }
}