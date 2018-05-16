package com.joe.shellclient.enums;

import lombok.Getter;

/**
 * @author: Joe
 * @description:
 * @date: Create in 15:46 2018/4/25
 */
@Getter
public enum PayStatusEnum implements CodeEnums {
    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功"),

    ;

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
