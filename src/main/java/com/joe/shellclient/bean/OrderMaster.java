package com.joe.shellclient.bean;

import com.joe.shellclient.enums.OrderStatusEnum;
import com.joe.shellclient.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: Joe
 * @description:
 * @date: Create in 15:44 2018/4/25
 */
@Entity
@Data
@DynamicUpdate
@EntityListeners({AuditingEntityListener.class})
public class OrderMaster {

    /**
     * 订单id.
     */
    @Id
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
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /**
     * 支付状态, 默认为0未支付.
     */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /**
     * 创建时间.
     */
    @CreatedDate
    private Date createTime;

    /**
     * 更新时间.
     */
    @UpdateTimestamp
    private Date updateTime;
}
