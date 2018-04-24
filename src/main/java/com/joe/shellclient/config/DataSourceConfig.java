package com.joe.shellclient.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author: Joe
 * @description:
 * @date: Create in 11:43 2018/4/24
 */
@Configuration
@Slf4j
public class DataSourceConfig {

    @Bean
    @Primary
    @Qualifier(value = "dataSource")
    @ConfigurationProperties(prefix = "c3p0")
    public DataSource dataSource() {
        log.info("========配置c3p0==========");
        return DataSourceBuilder.create().type(ComboPooledDataSource.class).build();
    }
}
