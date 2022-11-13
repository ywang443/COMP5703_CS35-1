package com.ezeat.advice;

import com.ezeat.constant.statusCode.impl.ResultCode;
import com.ezeat.exception.ApiException;
import com.ezeat.vo.ResultVo;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionAdvice {
    /**
     * 拦截前端传来的参数的校验器抛出的错误（ src/main/java/com/ezeat/validator）
     */
    @ExceptionHandler({BindException.class})
    public ResultVo MethodArgumentNotValidExceptionHandler(BindException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return new ResultVo(ResultCode.VALIDATE_ERROR.getCode(), objectError.getDefaultMessage(), ResultCode.VALIDATE_ERROR.getMsg());
        // 返回结果示例
        //  {
        //     "code": 3,
        //     "msg": "密码不能为空",
        //     "data": "参数校验失败"
        //  }
    }

    /**
     * 拦截程序抛出的ApiException
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ApiException.class)
    public ResultVo APIExceptionHandler(ApiException e) {
        // log.error(e.getMessage(), e); 由于还没集成日志框架，暂且放着，写上TODO
        return new ResultVo(e.getCode(), e.getMsg(), e.getMessage());
    }
}
