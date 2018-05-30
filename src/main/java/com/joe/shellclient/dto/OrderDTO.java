package com.joe.shellclient.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joe.shellclient.bean.OrderDetail;
import com.joe.shellclient.enums.OrderStatusEnum;
import com.joe.shellclient.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author: Joe
 * @description:
 * @date: Create in 15:11 2018/4/26
 */
@Data
public class OrderDTO {
    /**
     * 订单id.
     */
    private String orderId;

    /**
     * 买家名字.
     */
    private String buyerName;

    /**
     * 买家手机号.
     */
    private String buyerPhone;

    /**
     * 买家地址.
     */
    private String buyerAddress;

    /**
     * 买家微信Openid.
     */
    private String buyerOpenid;

    /**
     * 订单总金额.
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态, 默认为0新下单.
     */
    private Integer orderStatus;

    /**
     * 支付状态, 默认为0未支付.
     */
    private Integer payStatus;

    /**
     * 创建时间.
     */
    @JSONField(serializeUsing = Date2LongSerializer.class)
    private Date createTime;

    /**
     * 更新时间.
     */
    @JSONField(serializeUsing = Date2LongSerializer.class)
    private Date updateTime;

    List<OrderDetail> orderDetailList;


}
