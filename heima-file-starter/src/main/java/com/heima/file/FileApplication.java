package com.heima.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * <p>Project: heima-leadnews </p>
 * <p>Description: </p>
 * <p>Copyright (c) 2024 Karrytech (Shanghai/Beijing) Co., Ltd.</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhangkai@karrytech.com">Zhang Kai</a>
 */
@SpringBootApplication
@EnableConfigurationProperties
public class FileApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class, args);
    }
}