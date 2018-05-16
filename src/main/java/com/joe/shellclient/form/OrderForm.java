package com.joe.shellclient.form;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author: Joe
 * @description:
 * @date: Create in 14:22 2018/5/15
 */
@Data
public class OrderForm {
    /**
     * 买家姓名
     */
    @NotEmpty(message = "姓名必填")
    @JSONField(name = "name")
    private String buyerName;

    /**
     * 买家手机号
     */
    @NotEmpty(message = "手机号必填")
    @JSONField(name = "phone")
    private String buyerPhone;

    /**
     * 买家地址
     */
    @NotEmpty(message = "地址必填")
    @JSONField(name = "address")
    private String buyerAddress;

    /**
     * 买家微信openid
     */
    @NotEmpty(message = "openid必填")
    @JSONField(name = "openid")
    private String buyerOpenid;

    /**
     * 购物车
     */
    @NotEmpty(message = "购物车不能为空")
    private String items;
}
