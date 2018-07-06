package com.misakamikoto.springboot.api.config.database.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="spring.slave.datasource")
public class SlaveDatasourceProperties extends DatasourceProperties {
}
