package com.example.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品详情
 */
@Data
public class ProduceInfoVo {
    @JsonProperty("id")
    private String produceId;
    @JsonProperty("name")
    private String produceName;
    @JsonProperty("price")
    private BigDecimal producePrice;
    @JsonProperty("description")
    private String produceDiscription;
    @JsonProperty("icon")
    private String produceIcon;
}
