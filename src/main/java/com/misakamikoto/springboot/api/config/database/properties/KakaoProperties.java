package com.misakamikoto.springboot.api.config.database.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="spring.kakao")
@Data
public class KakaoProperties {
    String searchUrl;
    String appKey;
}
