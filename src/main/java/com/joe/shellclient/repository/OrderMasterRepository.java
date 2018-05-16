package com.joe.shellclient.repository;

import com.joe.shellclient.bean.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: Joe
 * @description:
 * @date: Create in 17:06 2018/4/25
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
    Page<OrderMaster> findByBuyerOpenid(String openId, Pageable pageable);
}
