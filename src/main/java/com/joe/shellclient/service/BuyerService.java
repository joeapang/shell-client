package com.joe.shellclient.service;

import com.joe.shellclient.dto.OrderDTO;

/**
 * @author: Joe
 * @description:
 * @date: Create in 15:04 2018/5/16
 */
public interface BuyerService {
    //查询一个订单
    OrderDTO findOrderOne(String openid, String orderId);

    //取消订单
    OrderDTO cancelOrder(String openid, String orderId);
}
