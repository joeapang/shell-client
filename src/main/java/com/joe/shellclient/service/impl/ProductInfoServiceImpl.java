package com.joe.shellclient.service.impl;

import com.joe.shellclient.bean.ProductInfo;
import com.joe.shellclient.dto.CartDTO;
import com.joe.shellclient.enums.ExceptionEnum;
import com.joe.shellclient.enums.ProductStatus;
import com.joe.shellclient.exception.SellException;
import com.joe.shellclient.repository.ProductInfoRepository;
import com.joe.shellclient.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Joe
 * @description:
 * @date: Create in 14:57 2018/4/23
 */
@Service("productInfoService")
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    ProductInfoRepository productInfoRepository;

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    @Override
    public ProductInfo findOne(String id) {
        return productInfoRepository.findOne(id);
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public List<ProductInfo> findAllOnSale(Pageable pageable) {
        return productInfoRepository.findByProductStatus(ProductStatus.OnSale.getCode(),pageable);
    }

    @Override
    public List<ProductInfo> findByProductStatus(Integer status,Pageable pageable) {
        return productInfoRepository.findByProductStatus(status, pageable);
    }

    @Override
    public List<ProductInfo> findByProductIdIn(List<String> id) {
        return productInfoRepository.findByProductIdIn(id);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOS) {
        cartDTOS.forEach(item->{
            ProductInfo productInfo=productInfoRepository.findOne(item.getProductId());
            if(productInfo==null){
                throw new SellException(ExceptionEnum.PRODUCT_NOT_EXIST);
            }
            Integer result=productInfo.getProductStock()+item.getProductQuantity();
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        });

    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOS) {
        cartDTOS.forEach(item->{
            ProductInfo productInfo=productInfoRepository.findOne(item.getProductId());
            if(productInfo==null){
                throw new SellException(ExceptionEnum.PRODUCT_NOT_EXIST);
            }
            Integer result=productInfo.getProductStock()-item.getProductQuantity();
            if (result<0){
                throw new SellException(ExceptionEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        });
    }
}
