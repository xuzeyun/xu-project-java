package com.xeg.xms.common.exception;

import org.springframework.security.core.AuthenticationException;

// 继承抽象的异常
public class CaptchaException extends AuthenticationException {
    // 重写一个方法
    public CaptchaException(String msg) {
        super(msg);
    }
}