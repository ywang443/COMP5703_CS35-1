package com.ezeat.constant.statusCode.impl;

import com.ezeat.constant.statusCode.StatusCode;
import lombok.Getter;


@Getter
public enum  AppCode implements StatusCode {
    TOKEN_ERROR(2000, "token异常"),
    APP_ERROR(2001, "业务异常"),
    PRICE_ERROR(2002, "价格异常");

    private int code;
    private String msg;

    AppCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
