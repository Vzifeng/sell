package com.example.sell.eunms;

import lombok.Getter;

@Getter
public enum OrderStatusEnums {
    NEW(0,"新订单"),
    FINISH(1,"完结"),
    CANCEL(2,"取消"),
    ;
    private Integer code;
    private String msg;

    OrderStatusEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
