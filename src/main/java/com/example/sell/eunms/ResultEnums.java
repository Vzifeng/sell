package com.example.sell.eunms;


import lombok.Getter;

@Getter
public enum ResultEnums {
    PRODUCE_NOT_EXIST(0,"商品不存在"),
    PRODUCE_NOT_ENOUGH(1,"商品库存不足"),
    ORDER_NOT_EXIST(2,"订单不存在"),
    ORDERDETAIL_NOT_EXIST(3,"订单详情不存在"),
    ORDER_NOT_UPDATE(4,"订单不能修改"),
    CANCEL_ORDER_FAIL(5,"取消订单失败"),
    OREDER_STATUS_NOT_RIGHT(6,"完结订单时订单状态不正确"),
    FAIL_FINISH_ORDER(7,"完结订单失败"),
    PAY_STATUS_NOT_RIGHT(8,"该订单不在支付范围"),
    PAY_FAIL(9,"支付失败"),
    PAYSTATUS_NOT_RIGHT(10,"支付状态不正确"),

    ;
    private Integer code;
    private String msg;

    ResultEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
