package com.example.sell.exception;

import com.example.sell.eunms.ResultEnums;
import io.swagger.models.auth.In;

public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResultEnums resultEnums) {
        super(resultEnums.getMsg());
        this.code = resultEnums.hashCode();
    }
}
