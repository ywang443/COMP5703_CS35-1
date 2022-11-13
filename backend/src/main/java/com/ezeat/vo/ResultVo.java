package com.ezeat.vo;

import com.ezeat.constant.statusCode.StatusCode;
import com.ezeat.constant.statusCode.impl.ResultCode;
import lombok.Data;

/**
 * 返回结果
 */
@Data
// @Accessors(chain = false) // 对应字段的 setter 方法调用后，会返回当前对象
public class ResultVo {

    // 状态码
    private int code;

    // 状态信息
    private String msg;

    // 返回对象
    private Object data;


    // 返回指定状态码，数据对象
    public ResultVo(StatusCode statusCode, Object data) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
        this.data = data;
    }

    // 手动设置返回vo
    public ResultVo(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultVo(String msg, Object data) {
        this.msg = msg;
        this.data = data;
    }

    // 默认返回成功状态码，数据对象
    public ResultVo(Object data) {
        this.code = ResultCode.SUCCESS.getCode();
        this.msg = ResultCode.SUCCESS.getMsg();
        this.data = data;
    }

    public ResultVo(String msg) {
        this.code = ResultCode.SUCCESS.getCode();
        this.msg = msg;
        this.data = null;
    }


    public ResultVo(Object data, String msg) {
        this.code = ResultCode.SUCCESS.getCode();
        this.msg = msg;
        this.data = data;
    }


    // 只返回状态码
    public ResultVo(StatusCode statusCode) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
        this.data = null;
    }

    public ResultVo(StatusCode statusCode, String msg) {
        this.code = statusCode.getCode();
        this.msg = msg;
        this.data = null;
    }
}
