package com.example.sell.po;

import io.swagger.models.auth.In;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class ProduceInfo {
    @Id
    private String produceId;
    //商品名称
    private String produceName;
    //商品单价
    private BigDecimal producePrice;
    //商品库存
    private Integer produceStock;
    //商品描述
    private String produceDiscription;
    //商品图片
    private String produceIcon;
    //商品状态，0正常1下架
    private Integer produceStatus;
    //类目编号
    private Integer categoryType;

}
