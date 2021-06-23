package com.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
//   后台打印日志级别
    @Bean
    Logger.Level loggerFeignConfig(){
        return Logger.Level.FULL;
    }
}
