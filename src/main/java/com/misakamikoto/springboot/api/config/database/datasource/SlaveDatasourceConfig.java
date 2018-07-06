package com.misakamikoto.springboot.api.config.database.datasource;

import com.misakamikoto.springboot.api.config.database.mapper.SlaveMapper;
import com.misakamikoto.springboot.api.config.database.properties.SlaveDatasourceProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@MapperScan(
        basePackages = "com.misakamikoto.springboot.api",
        annotationClass = SlaveMapper.class,
        sqlSessionFactoryRef = "slaveSqlSessionFactory")
@EnableTransactionManagement
public class SlaveDatasourceConfig extends DatasourceConfig {

    @Autowired
    SlaveDatasourceProperties slaveDatasourceProperties;

    @Bean(name = "slaveDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource() {
        return createDatasource(slaveDatasourceProperties);
    }
}