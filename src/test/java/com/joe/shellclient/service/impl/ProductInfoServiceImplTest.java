package com.joe.shellclient.service.impl;

import com.joe.shellclient.bean.ProductInfo;
import com.joe.shellclient.service.ProductInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author: Joe
 * @description:
 * @date: Create in 15:11 2018/4/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {
    @Autowired
    ProductInfoService productInfoService;

    @Test
    public void save() {
        ProductInfo product = new ProductInfo("12345", "牙刷", new BigDecimal(10.00), "生活用品", "//",
                2);
        System.out.println(product.getProductStatus());
        productInfoService.save(product);
    }

    @Test
    public void findOne() throws Exception {
        ProductInfo result = productInfoService.findOne("12345");
        System.out.println(result);
    }

    @Test
    public void findAll() throws Exception {
    }

    @Test
    public void findAllOnSale() throws Exception {
    }

    @Test
    public void findByProductStatus() throws Exception {
    }

}