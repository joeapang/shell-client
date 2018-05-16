package com.joe.shellclient.dto;

import lombok.Data;
import lombok.Getter;

/**
 * @author: Joe
 * @description:
 * @date: Create in 14:31 2018/5/4
 */
@Data
public class CartDTO {
    /**
     * 商品Id.
     */
    private String productId;

    /**
     * 数量.
     */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
