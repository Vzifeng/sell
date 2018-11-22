package com.example.sell.po;

import com.example.sell.eunms.OrderStatusEnums;
import com.example.sell.eunms.PayStatusEnums;
import io.swagger.models.auth.In;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class OrderMaster {
    /**订单ID*/
    @Id
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
    private Integer orderStatus = OrderStatusEnums.NEW.getCode();
    /**支付状态，0未支付（默认），1已支付*/
    private Integer payStatus = PayStatusEnums.WAIT.getCode();
    /**创建时间*/
    private Date createTime;
    /**更新时间*/
    private Date updateTime;
}
