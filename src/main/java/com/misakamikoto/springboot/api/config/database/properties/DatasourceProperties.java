package com.misakamikoto.springboot.api.config.database.properties;

import lombok.Data;

@Data
public class DatasourceProperties {
    String url;
    String driverClassName;
    String username;
    String password;
}
