package com.joe.shellclient.service;

import com.joe.shellclient.bean.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author: Joe
 * @description:
 * @date: Create in 14:47 2018/4/23
 */
public interface ProductInfoService {
    public ProductInfo save(ProductInfo productInfo);

    public ProductInfo findOne(String id);

    public Page<ProductInfo> findAll(Pageable pageable);

    public List<ProductInfo> findAllOnSale(Pageable pageable);


    public List<ProductInfo> findByProductStatus(Integer status,Pageable pageable);
}
