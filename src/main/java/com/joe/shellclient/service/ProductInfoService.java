package com.joe.shellclient.service;

import com.joe.shellclient.bean.ProductInfo;
import com.joe.shellclient.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author: Joe
 * @description:
 * @date: Create in 14:47 2018/4/23
 */
public interface ProductInfoService {
    ProductInfo save(ProductInfo productInfo);

    ProductInfo findOne(String id);

    Page<ProductInfo> findAll(Pageable pageable);

    List<ProductInfo> findAllOnSale(Pageable pageable);


    List<ProductInfo> findByProductStatus(Integer status, Pageable pageable);

    List<ProductInfo> findByProductIdIn(List<String> id);

    //增加库存

    void increaseStock(List<CartDTO> cartDTOS);
    //减少库存

    void decreaseStock(List<CartDTO> cartDTOList);

}
