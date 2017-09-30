package com.river.handler;

import java.util.HashMap;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: he.feng
 * \* Date: 2017/9/30
 * \* Description:
 * \
 */
public class ServiceException extends RuntimeException {

    private Map<String,String> messageMap;
    private String code;
    private String message;
    private Exception originException;

    public ServiceException(String message) {
        this.message = message;
    }

    public ServiceException(String message,String code) {
        this.message = message;
        this.code = code;
    }

    public ServiceException(String message,Exception originException) {
        this.message = message;
        this.originException = originException;
    }

    public ServiceException(String message,String code,Exception originException) {
        this.message = message;
        this.code = code;
        this.originException = originException;
    }


    public ServiceException(HashMap<String,String> messageMap, String message, String code) {
        this.messageMap = messageMap;
        this.message = message;
        this.code = code;
    }

    public ServiceException(HashMap<String,String> messageMap,String message,String code,Exception originException) {
        this.messageMap = messageMap;
        this.message = message;
        this.code = code;
        this.originException = originException;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public Map<String, String> getMessageMap() {
        return messageMap;
    }

    public String getCode() {
        return code;
    }

    public Exception getOriginException() {
        return originException;
    }

    @Override
    public String toString() {
        return "ServiceException{" +
                "messageMap=" + messageMap +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", originException=" + originException +
                '}';
    }
}
