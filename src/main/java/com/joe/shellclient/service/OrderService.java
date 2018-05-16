package com.joe.shellclient.service;

import com.joe.shellclient.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author: Joe
 * @description:
 * @date: Create in 14:55 2018/4/26
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param orderDTO
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);


    /**
     * 查询订单
     *
     * @param id
     * @return
     */
    OrderDTO findOne(String id);

    /**
     * 根据openId查询订单
     *
     * @param buyerOpenid
     * @param pageable
     * @return
     */
    Page<OrderDTO> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

    /**
     * 取消订单
     *
     * @param orderDTO
     * @return
     */
    OrderDTO cancel(OrderDTO orderDTO);

    /**
     * 完成订单
     *
     * @param orderDTO
     * @return
     */
    OrderDTO finish(OrderDTO orderDTO);

    /**
     * 支付订单
     *
     * @param orderDTO
     * @return
     */
    OrderDTO pay(OrderDTO orderDTO);
}
