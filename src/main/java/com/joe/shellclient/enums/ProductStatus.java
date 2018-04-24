package com.joe.shellclient.enums;

import lombok.Getter;

/**
 * @author: Joe
 * @description:
 * @date: Create in 15:30 2018/4/23
 */
@Getter
public enum ProductStatus implements CodeEnums {
    OnSale(0, "在售"),
    OffSale(1, "下架");


    private Integer code;

    private String message;

    ProductStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
