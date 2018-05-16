package com.joe.shellclient.bean;

import com.joe.shellclient.enums.ProductStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
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
 * @date: Create in 14:39 2018/4/23
 */
@Entity
@Data
@DynamicUpdate
@NoArgsConstructor
@EntityListeners({AuditingEntityListener.class})

public class ProductInfo {
    @Id
    private String productId;

    private String productName;

    private BigDecimal productPrice;

    /**
     * 库存.
     */

    private Integer productStock=0;

    private String productDescription;

    private String productIcon;

    /**
     * 状态, 0正常1下架.
     */
    private Integer productStatus = ProductStatus.OffSale.getCode();

    private Integer categoryType;

    /** 创建时间. */
    @CreatedDate
    private Date createTime;

    /** 更新时间. */
    @LastModifiedDate
    private Date updateTime;

    public ProductInfo(String productId, String productName, BigDecimal productPrice, String productDescription, String productIcon, Integer categoryType) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.productIcon = productIcon;
        this.categoryType = categoryType;
    }
}
