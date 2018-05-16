package com.joe.shellclient.exception;

import com.joe.shellclient.enums.ExceptionEnum;

/**
 * @author: Joe
 * @description:
 * @date: Create in 10:19 2018/4/28
 */
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ExceptionEnum message) {
        super(message.getMessage());
        this.code = message.getCode();

    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
