package com.xeg.xms.common.exception;

import com.xeg.xms.common.lang.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandle {
    // 非法数据异常
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 返回400的错
    @ExceptionHandler(value = IllegalAccessError.class)
    public Result handle(IllegalAccessError e){
        log.error("异常：！！！！！！！！！！！！！！！", e.getMessage());
        return Result.fail(e.getMessage());
    }

    // 权限异常
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 返回400的错
    @ExceptionHandler(value = RuntimeException.class)
    public Result handle(RuntimeException e){
        log.error("异常：！！！！！！！！！！！！！！！", e.getMessage());
        return Result.fail(e.getMessage());
    }
}
