package com.misakamikoto.springboot.api.config.database.datasource;

import com.misakamikoto.springboot.api.config.database.mapper.MasterMapper;
import com.misakamikoto.springboot.api.config.database.properties.MasterDatasourceProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@MapperScan(
        basePackages = "com.misakamikoto.springboot.api",
        annotationClass = MasterMapper.class,
        sqlSessionFactoryRef = "masterSqlSessionFactory")
@EnableTransactionManagement
public class MasterDatasourceConfig extends DatasourceConfig {

    @Autowired
    MasterDatasourceProperties masterDatasourceProperties;

    @Bean(name = "masterDataSource")
    @Primary
    public DataSource masterDataSource() {
        return createDatasource(masterDatasourceProperties);
    }
}