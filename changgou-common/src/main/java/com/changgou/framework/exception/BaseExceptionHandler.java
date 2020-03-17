package com.changgou.framework.exception;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author caoqian
 * @ClassName BaseExceptionHandler 公共异常处理类
 * @Date 2020/3/18 7:25
 * @Version 1.0
 */
@ControllerAdvice
public class BaseExceptionHandler {

    /**
     * 全局异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }
}
