package com.joe.shellclient.controller;

import com.joe.shellclient.dto.OrderDTO;
import com.joe.shellclient.enums.ExceptionEnum;
import com.joe.shellclient.exception.SellException;
import com.joe.shellclient.form.OrderForm;
import com.joe.shellclient.service.BuyerService;
import com.joe.shellclient.service.OrderService;
import com.joe.shellclient.utils.ResponseResultUtil;
import com.joe.shellclient.utils.converter.OrderForm2OrderDTOConverter;
import com.joe.shellclient.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Joe
 * @description:
 * @date: Create in 11:33 2018/5/15
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("/create")
    public ResponseResult<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确, orderForm={} errorMsg:{}", orderForm, bindingResult.getFieldError().getDefaultMessage());
            throw new SellException(ExceptionEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.converter(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车为空！");
            throw new SellException(ExceptionEnum.CART_EMPTY);
        }


        OrderDTO result = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", result.getOrderId());
        return ResponseResultUtil.success(map);
    }

    //订单列表
    @GetMapping("/list")
    public ResponseResult<List<OrderDTO>> list(@RequestParam(value = "openid") String openid,
                                               @RequestParam(value = "page", defaultValue = "0") Integer page,
                                               @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openid为空");
            throw new SellException(ExceptionEnum.PARAM_ERROR);
        }
        Page<OrderDTO> orderDTOPage = orderService.findByBuyerOpenid(openid, new PageRequest(page, size));
        return ResponseResultUtil.success(orderDTOPage.getContent());
    }

    //订单详情
    @GetMapping("/detail")
    public ResponseResult<OrderDTO> detail(@RequestParam(value = "openid") String openid,
                                           @RequestParam(value = "orderId") String orderId) {
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);

        return ResponseResultUtil.success(orderDTO);
    }

    //取消订单
    @PostMapping("/cancel")
    public ResponseResult cancel(@RequestParam(value = "openid") String openid,
                                 @RequestParam(value = "orderId") String orderId){
        buyerService.cancelOrder(openid,orderId);
        return ResponseResultUtil.success();
    }
}
