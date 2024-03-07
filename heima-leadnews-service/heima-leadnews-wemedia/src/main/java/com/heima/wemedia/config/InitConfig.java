package com.heima.wemedia.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 扫描openFeign服务降级的包
 * @author zhangkai
 */
@Configuration
@ComponentScan("com.heima.apis.article.fallback")
public class InitConfig {
}