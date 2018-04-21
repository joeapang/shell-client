package com.joe.shellclient.test.log;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: Joe
 * @description:
 * @date: Create in 11:29 2018/4/19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {
    private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void test() {
        String name="test";
        Integer age=17;
        logger.info("Info.......name: {}, age: {}",name,age);
        logger.debug("Debug........");
        logger.error("Error........");
    }
}
