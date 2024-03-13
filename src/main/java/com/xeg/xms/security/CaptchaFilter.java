package com.xeg.xms.security;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xeg.xms.common.exception.CaptchaException;
import com.xeg.xms.common.lang.Const;
import com.xeg.xms.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 图片验证码过滤器
@Component
public class CaptchaFilter extends OncePerRequestFilter {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    LoginFailureHandler loginFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // 获取当前请求的url
        String url = httpServletRequest.getRequestURI();
        // 判断url中是否包含/login，并且请求方式为post
        if ("/login".equals(url) && httpServletRequest.getMethod().equals("POST")) {
            try{
                // 校验验证码
                validate(httpServletRequest);
            } catch (CaptchaException e) {
                // 交给认证失败处理器
                loginFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
            }
        }

        // 如果不是login过滤器链往后面走
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    // 校验验证码逻辑
    private void validate(HttpServletRequest httpServletRequest) {
        // 拿到用户输入的code 和 用来获取radis中code的key
        String code = httpServletRequest.getParameter("code");
        String key = httpServletRequest.getParameter("token");

        // 非空判断，如果是空没必要校验（返回错误）
        if (StringUtils.isBlank(code) || StringUtils.isBlank(key)) {
            // 抛出异常
            throw new CaptchaException("验证码错误");
        }

        // 判断code是否等于radis中存储的code（如果不等于，抛出异常）
        if (!code.equals(redisUtil.hget(Const.CAPTCHA_KEY, key))) {
            throw new CaptchaException("验证码错误");
        }

        // 如果code没问题，把radis中的验证码去掉（一次性使用）
        redisUtil.hdel(Const.CAPTCHA_KEY, key);
    }
}