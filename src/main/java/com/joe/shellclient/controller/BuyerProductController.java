package com.joe.shellclient.controller;

import com.joe.shellclient.bean.ProductCategory;
import com.joe.shellclient.bean.ProductInfo;
import com.joe.shellclient.service.ProductCategoryService;
import com.joe.shellclient.service.ProductInfoService;
import com.joe.shellclient.utils.ResponseResultUtil;
import com.joe.shellclient.vo.ProductInfoVo;
import com.joe.shellclient.vo.ProductVo;
import com.joe.shellclient.vo.ResponseResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Joe
 * @description:
 * @date: Create in 10:34 2018/4/24
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {


    @Autowired
    ProductCategoryService categoryService;

    @Autowired
    ProductInfoService productInfoService;

    @GetMapping("/list")
    public ResponseResult list() {
        //查询所有上架商品
        List<ProductInfo> productInfoList = productInfoService.findAllOnSale(new PageRequest(0, 20));
        //查询所需要的类目
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //数据拼装
        List<ProductVo> productVoList=new ArrayList<>();
        categoryList.stream().forEach(e -> {
            ProductVo productVo = new ProductVo();
            productVo.setCategoryName(e.getCategoryName());
            productVo.setCategoryType(e.getCategoryType());
            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            productInfoList.stream().forEach(t -> {
                if (e.getCategoryType().equals(t.getCategoryType())) {
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(t, productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            });
            productVo.setList(productInfoVoList);
            productVoList.add(productVo);
        });
        return ResponseResultUtil.success(productVoList, "成功!");
//             new ResponseResult(1, "成功",
//                new ProductVo("生活用品", 2,
//                        Arrays.asList(
//                                new ProductInfoVo("1", "牙膏",
//                                        new BigDecimal(1.0), "生活用品", "//"))));
    }
}
