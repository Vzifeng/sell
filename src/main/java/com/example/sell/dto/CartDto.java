package com.example.sell.dto;

import io.swagger.models.auth.In;
import lombok.Data;

//购物车
@Data
public class CartDto {
    //商品Id
    private String produceId;
    //商品数量
    private Integer produceQuantity;

    public CartDto(){}

    public CartDto(String produceId, Integer produceQuantity) {
        this.produceId = produceId;
        this.produceQuantity = produceQuantity;
    }
}
