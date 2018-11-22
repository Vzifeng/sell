package com.example.sell.service.impl;

import com.example.sell.dto.OrderDto;
import com.example.sell.eunms.OrderStatusEnums;
import com.example.sell.eunms.PayStatusEnums;
import com.example.sell.po.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {
    @Autowired
    OrderServiceImpl orderService;
    @Test
    public void create() {
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerOpenid("yang");
        orderDto.setBuyerName("骷髅");
        orderDto.setBuyerPhone("13645623698");
        orderDto.setBuyerAddress("驾驶位");
        List<OrderDetail> orderDtoList = new ArrayList <>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProduceId("123456789");
        orderDetail.setProduceQuantity(2);
        orderDtoList.add(orderDetail);
        orderDto.setOrderDetail(orderDtoList);
        OrderDto orderDto1 = orderService.create(orderDto);
        log.info("结果:orderDto1={}",orderDto1);
        Assert.assertNotNull(orderDto1);
    }

    @Test
    public void findOne() {
        OrderDto orderDto =  orderService.findOne("ffe6b5b600de466eb34d22c2cac2f276");
        log.info("结果："+orderDto);
        Assert.assertEquals("ffe6b5b600de466eb34d22c2cac2f276",orderDto.getOrderId());
    }

    @Test
    public void findOrderList() {
        PageRequest pageRequest = new PageRequest(1,3);
        Page<OrderDto> orderDtoPage = orderService.findOrderList("yang",pageRequest);
        Assert.assertNotEquals(0,orderDtoPage.getTotalElements());
    }
    //取消订单
    @Test
    public void cancel() {
        OrderDto orderDto = orderService.findOne("ffe6b5b600de466eb34d22c2cac2f276");
        OrderDto result = orderService.cancel(orderDto);
        Assert.assertEquals(OrderStatusEnums.CANCEL.getCode(),result.getOrderStatus());
    }
    //完结订单
    @Test
    public void finish() {
        OrderDto orderDto = orderService.findOne("340e855e44e3427aa9b4cfb3b472575f");
        OrderDto result = orderService.finish(orderDto);
        Assert.assertEquals(OrderStatusEnums.FINISH.getCode(),result.getOrderStatus());

    }
    //支付订单
    @Test
    public void pay() {
        OrderDto orderDto = orderService.findOne("340e855e44e3427aa9b4cfb3b472575f");
        OrderDto result = orderService.pay(orderDto);
        Assert.assertEquals(PayStatusEnums.SUCCESS.getCode(),result.getPayStatus());
    }
}