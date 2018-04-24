package com.joe.shellclient.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: Joe
 * @description:
 * @date: Create in 11:59 2018/4/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfoVo implements Serializable {


    private static final long serialVersionUID = -8593693482874746788L;
    @JSONField(name = "id")
    private String productId;

    @JSONField(name = "name")
    private String productName;

    @JSONField(name = "price")
    private BigDecimal productPrice;

    @JSONField(name= "description")
    private String productDescription;

    @JSONField(name= "icon")
    private String productIcon;
}
