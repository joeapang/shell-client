package com.joe.shellclient.service.impl;

import com.joe.shellclient.dto.OrderDTO;
import com.joe.shellclient.enums.ExceptionEnum;
import com.joe.shellclient.exception.SellException;
import com.joe.shellclient.service.BuyerService;
import com.joe.shellclient.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;

/**
 * @author: Joe
 * @description:
 * @date: Create in 15:04 2018/5/16
 */
@Service("buyerService")
@Slf4j
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrder(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO=checkOrder(openid,orderId);
        if (orderDTO == null) {
            log.error("【取消订单】查不到改订单, orderId={}", orderId);
            throw new SellException(ExceptionEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }


    public OrderDTO checkOrder(String openid, String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            return null;
        }

        if (!orderDTO.getBuyerOpenid().equals(openid)) {
            log.error("【查询订单】订单的openid不一致. openid={}, orderDTO={}", openid, orderDTO);
            throw new SellException(ExceptionEnum.ORDER_OWNER_ERROR);
        }

        return orderDTO;
    }
}
