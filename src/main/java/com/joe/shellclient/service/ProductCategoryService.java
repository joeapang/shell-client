package com.joe.shellclient.service;

import com.joe.shellclient.bean.ProductCategory;

import java.util.List;

/**
 * @author: Joe
 * @description:
 * @date: Create in 11:27 2018/4/23
 */
public interface ProductCategoryService {
    ProductCategory findOne(Integer id);
    List<ProductCategory> findAll();
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypes);
}
