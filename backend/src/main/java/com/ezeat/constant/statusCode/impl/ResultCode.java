package com.ezeat.constant.statusCode.impl;

import com.ezeat.constant.statusCode.StatusCode;
import lombok.Getter;

/**
 * 全局状态码
 */
@Getter
public enum ResultCode implements StatusCode {
    // 请求相关
    SUCCESS(0, "请求成功"),
    FAILED(1, "请求失败"),
    VALIDATE_ERROR(3, "参数校验失败"),
    RESPONSE_PACK_ERROR(4, "response返回包装失败"),
    UNKNOWN_ERROR(5, "未知错误"),
    // 用户相关
    NOT_LOGIN(10, "未登陆"),
    USER_NOT_EXIST(11, "用户不存在"),
    USER_IS_EXIST(12, "用户已存在"),
    PASSWORD_ERROR(13, "密码错误"),
    AUTH_ERROR(14, "无操作权限"),

    // 数据库相关
    NOT_FOUND(20, "未找到记录"),
    LIST_EMPTY(21, "记录列表为空"),
    UPDATE_ERROR(22, "记录更新失败"),
    INSERT_ERROR(23, "插入记录失败");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
