package com.joe.shellclient.service.impl;

import com.joe.shellclient.bean.OrderDetail;
import com.joe.shellclient.dto.OrderDTO;
import com.joe.shellclient.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author: Joe
 * @description:
 * @date: Create in 11:05 2018/5/7
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    private final String BUYER_OPENID = "1101110";

    @Test
    public void create() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerOpenid(BUYER_OPENID);
        orderDTO.setBuyerName("Joe");
        orderDTO.setBuyerPhone("18883284308");
        orderDTO.setBuyerAddress("重庆");

        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setProductId("12345");
        orderDetail1.setProductQuantity(2);

        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setProductId("12245");
        orderDetail2.setProductQuantity(1);

        orderDetailList.add(orderDetail1);
        orderDetailList.add(orderDetail2);
        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result = orderService.create(orderDTO);
        log.info("【创建订单】result={}", result);
        assert result != null : "something is wrong here ";
    }

    @Test
    public void findOne() throws Exception {
        OrderDTO orderDTO = orderService.findOne("1525673113975630280");
        log.info("【查找订单】result={}", orderDTO);
    }

    @Test
    public void findByBuyerOpenid() throws Exception {
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<OrderDTO> result = orderService.findByBuyerOpenid("1101110", pageRequest);
        log.info("【查找用户订单订单】result={}", result.getContent());

    }

    @Test
    public void cancel() throws Exception {
    }

    @Test
    public void finish() throws Exception {
    }

    @Test
    public void pay() throws Exception {
    }

}