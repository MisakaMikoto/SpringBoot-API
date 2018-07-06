package com.misakamikoto.springboot.api.config.database.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="spring.master.datasource")
public class MasterDatasourceProperties extends DatasourceProperties {
}
