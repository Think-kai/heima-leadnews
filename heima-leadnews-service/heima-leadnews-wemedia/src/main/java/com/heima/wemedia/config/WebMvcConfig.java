package com.heima.wemedia.config;

import com.heima.wemedia.interceptor.WmTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>Project: heima-leadnews </p>
 * <p>Description: </p>
 * <p>Copyright (c) 2024 Karrytech (Shanghai/Beijing) Co., Ltd.</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhangkai@karrytech.com">Zhang Kai</a>
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new WmTokenInterceptor()).addPathPatterns("/**");
    }
}