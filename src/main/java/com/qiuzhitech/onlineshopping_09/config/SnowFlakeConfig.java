package com.qiuzhitech.onlineshopping_09.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SnowFlakeConfig{
    @Bean
    public UtilService utilService(){
        return new UtilService(0, 0);
    }
}
