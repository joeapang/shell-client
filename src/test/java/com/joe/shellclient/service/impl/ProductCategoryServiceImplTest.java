package com.joe.shellclient.service.impl;

import com.joe.shellclient.bean.ProductCategory;
import com.joe.shellclient.service.ProductCategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author: Joe
 * @description:
 * @date: Create in 11:40 2018/4/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired
    ProductCategoryService service;

    @Test
    public void findAllTest(){
        List<ProductCategory> result=service.findAll();
        assert result.size()>0:"something is wrong here ";
        System.out.println(result);
    }
}