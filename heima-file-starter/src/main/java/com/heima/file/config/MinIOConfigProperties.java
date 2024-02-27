package com.heima.file.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Project: heima-leadnews </p>
 * <p>Copyright (c) 2024 Karrytech (Shanghai/Beijing) Co., Ltd.</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhangkai@karrytech.com">Zhang Kai</a>
 */
@Data
@ConfigurationProperties(prefix = "minio")  // 文件上传 配置前缀file.oss
public class MinIOConfigProperties {

    private String accessKey;
    private String secretKey;
    private String bucket;
    private String endpoint;
    private String readPath;


}