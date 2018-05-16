package com.joe.shellclient.repository;

import com.joe.shellclient.bean.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * @author: Joe
 * @description:
 * @date: Create in 14:25 2018/4/21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Test
    public void findOneTest() {
        ProductCategory productCategory = productCategoryRepository.findOne(2);
//        Assert.assertNotNull("something is wrong here",productCategory);
        assert productCategory != null : "something is wrong here";
        System.out.println(productCategory.toString());
    }

    @Test
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory("生活用品", 3);
        ProductCategory result = productCategoryRepository.save(productCategory);

        System.out.println(result);
    }

    @Test
    public void updateTest() {
        ProductCategory productCategory = productCategoryRepository.findOne(1);
        productCategory.setCategoryType(3);

        ProductCategory result = productCategoryRepository.save(productCategory);
        System.out.println(result);
    }

    @Test
    public void findByCategoryTypeTest() {
        List<Integer> categoryTypeList = Arrays.asList(2,3,4);
        List<ProductCategory> result = productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
        assert result.size()>0:"something is wrong here";
    }
}