package com.joe.shellclient.utils.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.support.jaxrs.FastJsonProvider;
import com.joe.shellclient.bean.OrderDetail;
import com.joe.shellclient.dto.OrderDTO;
import com.joe.shellclient.enums.ExceptionEnum;
import com.joe.shellclient.exception.SellException;
import com.joe.shellclient.form.OrderForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Joe
 * @description:
 * @date: Create in 14:29 2018/5/15
 */
@Slf4j
public class OrderForm2OrderDTOConverter {
    public static OrderDTO converter(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> orderDetails = new ArrayList<>();

        try {
            orderDetails = JSON.parseObject(orderForm.getItems(), new TypeReference<List<OrderDetail>>() {
            });

        } catch (Exception e) {
            log.error("【对象转换】错误, string={}", orderForm.getItems());
            throw new SellException(ExceptionEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetails);
        return orderDTO;
    }
}
