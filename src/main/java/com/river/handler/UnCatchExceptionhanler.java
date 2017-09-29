package com.river.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: he.feng
 * \* Date: 2017/9/29
 * \* Description: 处理未捕获异常
 * \
 */
@ControllerAdvice
public class UnCatchExceptionhanler {

    private static final Logger logger = LoggerFactory.getLogger(UnCatchExceptionhanler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String resolveException(HttpServletRequest request,Exception e) {
        logger.error("访问url【" + request.getRequestURI() + "]出错，错误信息",e);


        return null;
    }
}
