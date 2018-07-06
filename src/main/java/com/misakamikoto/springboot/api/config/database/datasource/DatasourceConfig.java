package com.misakamikoto.springboot.api.config.database.datasource;

import com.misakamikoto.springboot.api.config.database.properties.DatasourceProperties;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class DatasourceConfig {

    protected<T extends DatasourceProperties> DataSource createDatasource(T properties) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(properties.getDriverClassName());
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        return dataSource;
    }
}
