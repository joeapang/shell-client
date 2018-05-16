package com.joe.shellclient.repository;

import com.joe.shellclient.bean.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: Joe
 * @description:
 * @date: Create in 17:18 2018/4/25
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    List<OrderDetail> findByOrderId(String orderId);
}
