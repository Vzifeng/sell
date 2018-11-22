package com.example.sell.eunms;

import lombok.Getter;

/**
 * 商品状态枚举
 */
@Getter
public enum  ProduceStatusEnums {
    UP(0,"在架"),
    DOWN(1,"下架");

    private Integer code;
    private String message;

    ProduceStatusEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
