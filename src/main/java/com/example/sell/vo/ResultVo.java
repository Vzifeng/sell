package com.example.sell.vo;

import lombok.Data;

/**
 * http外层返回的对象
 */
@Data
public class ResultVo<T> {
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 返回结果
     */
    private T data;

    public ResultVo() {

    }
    public ResultVo(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
