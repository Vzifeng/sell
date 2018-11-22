package com.example.sell.dao;

import com.example.sell.po.OrderDetail;
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
public class OrderDetailDaoTest {
    @Autowired
    OrderDetailDao orderDetailDao;

    @Test
    public void findByOrderId() {
        List<OrderDetail> list = orderDetailDao.findByOrderId("123456");
        Assert.assertNotEquals(0,list.size());
    }
    @Test
    public void save(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1234577");
        orderDetail.setOrderId("12345677");
        orderDetail.setProduceId("123456789");
        orderDetail.setProduceName("鸡翅翅");
        orderDetail.setProducePrice(new BigDecimal(25.66));
        orderDetail.setProduceIcon("http://xxxxx.jpg");
        orderDetail.setProduceQuantity(4);
        OrderDetail orderDetail1 = orderDetailDao.save(orderDetail);
        Assert.assertNotNull(orderDetail1);
    }
}