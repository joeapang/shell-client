package com.joe.shellclient.repository;

import com.joe.shellclient.bean.ProductInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: Joe
 * @description:
 * @date: Create in 14:44 2018/4/23
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {
    List<ProductInfo> findByProductStatus(Integer status, Pageable pageable);
}
