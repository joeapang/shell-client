package com.joe.shellclient.enums;

import lombok.Getter;

/**
 * @author: Joe
 * @description:
 * @date: Create in 15:45 2018/4/25
 */
@Getter
public enum OrderStatusEnum implements CodeEnums {
    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "已取消"),
    ;

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
