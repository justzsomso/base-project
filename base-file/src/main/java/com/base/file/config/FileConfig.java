package com.base.file.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "file")
public class FileConfig {
    private String storageType; // local/oss

    private LocalConfig local;

    private OssConfig oss;

    @Data
    public static class LocalConfig {
        private String basePath;
        private Long maxSize; // 单位：字节
    }

    @Data
    public static class OssConfig {
        private String endpoint;
        private String accessKey;
        private String secretKey;
        private String bucketName;
    }
}