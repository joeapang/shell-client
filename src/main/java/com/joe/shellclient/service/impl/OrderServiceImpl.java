package com.joe.shellclient.service.impl;

import com.joe.shellclient.bean.OrderDetail;
import com.joe.shellclient.bean.OrderMaster;
import com.joe.shellclient.bean.ProductInfo;
import com.joe.shellclient.dto.CartDTO;
import com.joe.shellclient.dto.OrderDTO;
import com.joe.shellclient.enums.ExceptionEnum;
import com.joe.shellclient.enums.OrderStatusEnum;
import com.joe.shellclient.enums.PayStatusEnum;
import com.joe.shellclient.exception.SellException;
import com.joe.shellclient.repository.OrderDetailRepository;
import com.joe.shellclient.repository.OrderMasterRepository;
import com.joe.shellclient.repository.ProductInfoRepository;
import com.joe.shellclient.service.OrderService;
import com.joe.shellclient.service.ProductInfoService;
import com.joe.shellclient.utils.KeyUtils;
import com.sun.media.jfxmedia.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Joe
 * @description:
 * @date: Create in 15:59 2018/4/26
 */
@Service("orderService")
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    ProductInfoService productInfoService;
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    OrderMasterRepository orderMasterRepository;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        BigDecimal[] orderAmount = {new BigDecimal(BigInteger.ZERO)};
        String orderId = KeyUtils.genUniqueKey();
        //1.查询商品（价格，库存）

        List<OrderDetail> orderDetails = orderDTO.getOrderDetailList();
        orderDetails.forEach(orderDetail -> {
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ExceptionEnum.PRODUCT_NOT_EXIST);
            }

            //2.计算订单总价
            orderAmount[0] = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount[0]);
            //订单详情入库
            orderDetail.setDetailId(KeyUtils.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);
        });
        //3.创建订单（插入orderMaster和OrderDetail）
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount[0]);
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMasterRepository.save(orderMaster);
        //4.更新库存
        List<CartDTO> result = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productInfoService.decreaseStock(result);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String id) {
        OrderMaster orderMaster = orderMasterRepository.findOne(id);
        if (orderMaster == null) {
            throw new SellException(ExceptionEnum.PRODUCT_NOT_EXIST);
        }

        List<OrderDetail> detailList = orderDetailRepository.findByOrderId(id);
        if (CollectionUtils.isEmpty(detailList)) {
            throw new SellException(ExceptionEnum.ORDER_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(detailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findByBuyerOpenid(String buyerOpenid, Pageable pageable) {

        Page<OrderMaster> orderMasters = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDTO> orderDTOS = orderMasters.getContent().stream().map(e -> {
            List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(e.getOrderId());
            OrderDTO orderDTO = new OrderDTO();
            BeanUtils.copyProperties(e, orderDTO);
            orderDTO.setOrderDetailList(orderDetails);
            return orderDTO;
        }).collect(Collectors.toList());
        return new PageImpl<>(orderDTOS, pageable, orderMasters.getTotalElements());
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【取消订单】订单状态不正确，orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ExceptionEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态

        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMasterRepository.save(orderMaster);
        //返回库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【取消订单】订单详情中无商品，orderId={},OrderDetailList={}", orderDTO.getOrderId(), orderDTO.getOrderDetailList());
            throw new SellException(ExceptionEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOS = orderDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        productInfoService.increaseStock(cartDTOS);
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【完结订单】订单状态不正确，orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ExceptionEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster result = orderMasterRepository.save(orderMaster);
        if (result == null) {
            throw new SellException(ExceptionEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO pay(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【订单支付完成】订单状态不正确，orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ExceptionEnum.ORDER_STATUS_ERROR);
        }

        //判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("【订单支付完成】订单支付状态不正确, orderDTO={}", orderDTO);
            throw new SellException(ExceptionEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster result = orderMasterRepository.save(orderMaster);
        if (result == null) {
            log.error("【订单支付完成】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ExceptionEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }
}
