package com.joe.shellclient.repository;

import com.joe.shellclient.bean.OrderMaster;
import org.hibernate.criterion.Order;
import org.hibernate.sql.OracleJoinFragment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * @author: Joe
 * @description:
 * @date: Create in 11:03 2018/4/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123456");
        orderMaster.setBuyerName("joe");
        orderMaster.setBuyerOpenid("abc123");
        orderMaster.setBuyerPhone("18883284308");
        orderMaster.setBuyerAddress("重庆");
        orderMaster.setOrderAmount(new BigDecimal(12345));
        OrderMaster result=repository.save(orderMaster);
        assert result != null:"there is something wrong here!";
        System.out.println(result);
    }

    @Test
    public void updateTest(){
        OrderMaster orderMaster=repository.findOne("123456");
        orderMaster.setBuyerAddress("安徽");
        OrderMaster result=repository.save(orderMaster);
        assert result != null:"there is something wrong here!";
        System.out.println(result);
    }


}