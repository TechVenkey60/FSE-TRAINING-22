package com.vrk.tms.service.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
@Slf4j
public class TmsDBConfig {
    @Autowired
    private DbConfigProperties configProperties;

    @Bean
    public DataSource getDataSource(DbConfigProperties configProperties){
        log.debug("Creating the DataSource For Connection");
        return DataSourceBuilder.create()
                .url(configProperties.getUrl())
                .username(configProperties.getUserName())
                .password(configProperties.getPassword())
                .build();
    }
}
