package com.joe.shellclient.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author: Joe
 * @description:
 * @date: Create in 11:55 2018/4/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVo implements Serializable {
    private static final long serialVersionUID = -7539743071966144155L;

    @JSONField(name = "name")
    private String categoryName;

    @JSONField(name = "type")
    private Integer categoryType;

    @JSONField(name = "foods")
    private List<ProductInfoVo> list;

}
