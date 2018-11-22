package com.example.sell.service.impl;

import com.example.sell.dao.OrderDetailDao;
import com.example.sell.dao.OrderMasterDao;
import com.example.sell.dto.CartDto;
import com.example.sell.dto.OrderDto;
import com.example.sell.eunms.OrderStatusEnums;
import com.example.sell.eunms.PayStatusEnums;
import com.example.sell.eunms.ResultEnums;
import com.example.sell.exception.SellException;
import com.example.sell.po.OrderDetail;
import com.example.sell.po.OrderMaster;
import com.example.sell.po.ProduceInfo;
import com.example.sell.service.OrderService;
import com.example.sell.service.ProduceInfoService;
import com.example.sell.utils.OrderMasterZOrderDtoConverter;
import com.example.sell.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    ProduceInfoService produceInfoService;
    @Autowired
    OrderDetailDao orderDetailDao;
    @Autowired
    OrderMasterDao orderMasterDao;
    @Override
    @Transactional
    public OrderDto create(OrderDto orderDto) {
        //查询商品
        BigDecimal totalMoney = new BigDecimal(0);
        //订单Id
        String orderId = UUIDUtil.getUUID();
        for (OrderDetail orderDetail : orderDto.getOrderDetail()) {
            ProduceInfo produceInfo = produceInfoService.getOne(orderDetail.getProduceId());
            if (produceInfo == null) {
                throw new SellException(ResultEnums.PRODUCE_NOT_EXIST);
            }
            //计算总价
            totalMoney = produceInfo.getProducePrice().multiply(new BigDecimal(orderDetail.getProduceQuantity())).add(totalMoney);
            //订单详情入库
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(UUIDUtil.getUUID());
            BeanUtils.copyProperties(produceInfo,orderDetail);
            orderDetailDao.save(orderDetail);
        }
        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(totalMoney);
        orderMaster.setOrderStatus(OrderStatusEnums.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnums.WAIT.getCode());
        orderMasterDao.save(orderMaster);
        //扣库存
        List<CartDto> list = orderDto.getOrderDetail().stream()
                .map(e -> new CartDto(e.getProduceId(),e.getProduceQuantity()))
                .collect(Collectors.toList());
        produceInfoService.decreaseStock(list);
        return orderDto;
    }
    //查询订单
    @Override
    public OrderDto findOne(String orderId) {
        OrderDto orderDto = new OrderDto();
        OrderMaster orderMaster = orderMasterDao.getOne(orderId);
        if (orderMaster == null){
            throw new SellException(ResultEnums.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetail = orderDetailDao.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetail)){
            throw new SellException(ResultEnums.ORDERDETAIL_NOT_EXIST);
        }
        BeanUtils.copyProperties(orderMaster,orderDto);
        orderDto.setOrderDetail(orderDetail);
        return orderDto;
    }
    //查询订单列表
    @Override
    public Page<OrderDto> findOrderList(String buyerOpenid, Pageable pageable) {
        //PageRequest pageRequest = new PageRequest(1,3);
        Page <OrderMaster> orderMasterList = orderMasterDao.findByBuyerOpenid("yang", pageable);

        List<OrderDto> orderDtoList = OrderMasterZOrderDtoConverter.convert(orderMasterList.getContent());
        Page<OrderDto> orderDtoPage = new PageImpl(orderDtoList,pageable,orderMasterList.getTotalElements());
        return orderDtoPage;
    }
    //取消订单
    @Override
    @Transactional
    public OrderDto cancel(OrderDto orderDto) {
        OrderMaster orderMaster = new OrderMaster();
        //判断订单状态
        if (!orderDto.getOrderStatus().equals(OrderStatusEnums.NEW.getCode())){
            log.error("不能取消该订单");
            throw new SellException(ResultEnums.ORDER_NOT_UPDATE);
        }
        //修改订单状态
        orderDto.setOrderStatus(OrderStatusEnums.CANCEL.getCode());
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster result = orderMasterDao.save(orderMaster);
        if (result == null){
            log.error("取消订单失败");
            throw new SellException(ResultEnums.CANCEL_ORDER_FAIL);
        }
        //返回库存
        if (CollectionUtils.isEmpty(orderDto.getOrderDetail())){
            log.error("【取消订单】订单中无商品详情");
            throw new SellException(ResultEnums.ORDERDETAIL_NOT_EXIST);
        }
        List<CartDto> cartDtoList = orderDto.getOrderDetail().stream()
                .map(e -> new CartDto(e.getProduceId(),e.getProduceQuantity()))
                .collect(Collectors.toList());
        produceInfoService.increaseStock(cartDtoList);
        //若已支付，退款
        if (orderDto.getPayStatus().equals(PayStatusEnums.SUCCESS.getCode())){
            //TODO
        }
        return orderDto;
    }
    //完结订单
    @Override
    @Transactional
    public OrderDto finish(OrderDto orderDto) {
        //判断订单状态
        if (!orderDto.getOrderStatus().equals(OrderStatusEnums.NEW.getCode())){
            log.error("【完结订单】订单状态不正确,orderId={}",orderDto.getOrderId());
            throw new SellException(ResultEnums.OREDER_STATUS_NOT_RIGHT);
        }
        //修改订单状态
        OrderMaster orderMaster = new OrderMaster();
        orderDto.setOrderStatus(OrderStatusEnums.FINISH.getCode());
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster result = orderMasterDao.save(orderMaster);
        if (result == null){
            log.error("【完结订单】,完结订单失败");
            throw new SellException(ResultEnums.FAIL_FINISH_ORDER);
        }
        return orderDto;
    }
    //支付订单
    @Override
    @Transactional
    public OrderDto pay(OrderDto orderDto) {
        //判断订单状态
        if (!orderDto.getOrderStatus().equals(OrderStatusEnums.NEW.getCode())){
            log.error("【支付订单】，该订单不在支付范围");
            throw new SellException(ResultEnums.PAY_STATUS_NOT_RIGHT);
        }
        //判断支付状态
        if (!orderDto.getPayStatus().equals(PayStatusEnums.WAIT.getCode())){
            log.error("【支付订单】，支付状态不正确");
            throw new SellException(ResultEnums.PAYSTATUS_NOT_RIGHT);
        }
        orderDto.setPayStatus(PayStatusEnums.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster result =  orderMasterDao.save(orderMaster);
        if (result == null){
            log.error("【支付订单】，支付失败");
            throw new SellException(ResultEnums.PAY_FAIL);
        }
        return orderDto;
    }
}
