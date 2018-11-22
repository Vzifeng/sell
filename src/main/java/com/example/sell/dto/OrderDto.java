package com.example.sell.dto;
import com.example.sell.po.OrderDetail;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDto {
    /**订单ID*/
    private String orderId;
    /**买家名称*/
    private String buyerName;
    /**买家电话*/
    private String buyerPhone;
    /**买家地址*/
    private String buyerAddress;
    /**买家微信ID*/
    private String buyerOpenid;
    /**订单金额*/
    private BigDecimal orderAmount;
    /**订单状态，0为新下单（默认），1完结的订单，2取消的订单*/
    private Integer orderStatus;
    /**支付状态，0未支付（默认），1已支付*/
    private Integer payStatus;
    /**创建时间*/
    private Date createTime;
    /**更新时间*/
    private Date updateTime;
    /**订单详情*/
    private List<OrderDetail> orderDetail;
}
