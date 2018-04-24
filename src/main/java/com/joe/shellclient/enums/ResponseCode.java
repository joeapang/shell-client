package com.joe.shellclient.enums;

import lombok.Getter;
import org.aspectj.apache.bcel.classfile.Code;

/**
 * @author: Joe
 * @description:
 * @date: Create in 10:45 2018/4/24
 */
@Getter
public enum ResponseCode implements CodeEnums {

    SUCCESS(0, "SUCCESS"),
    ERROR(1, "ERROR");

    private Integer code;
    private String msg;

    ResponseCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
