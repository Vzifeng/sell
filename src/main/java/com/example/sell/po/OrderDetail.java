package com.example.sell.po;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class OrderDetail {
    /**
     * 订单详情Id
     */
    @Id
    private String detailId;
    /**
     * 订单Id
     */
    private String orderId;
    /**商品Id*/
    private  String produceId;
    /**商品名称*/
    private String produceName;
    /**商品单价*/
    private BigDecimal producePrice;
    /**商品数量*/
    private Integer produceQuantity;
    /**商品图片*/
    private String produceIcon;

    private Date createTime;

    private Date updateTime;
}
