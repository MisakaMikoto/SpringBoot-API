package com.misakamikoto.springboot.api.config.database.session;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SqlSessionTemplateConfig {

    @Bean(name = "masterSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate masterSessionTemplate(@Qualifier("masterSqlSessionFactory") SqlSessionFactory masterSessionFactory) {
        return this.createSessionTemplate(masterSessionFactory);
    }

    @Bean(name = "slaveSqlSessionTemplate")
    public SqlSessionTemplate slaveSessionTemplate(@Qualifier("slaveSqlSessionFactory") SqlSessionFactory slaveSessionFactory) {
        return this.createSessionTemplate(slaveSessionFactory);
    }

    private SqlSessionTemplate createSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
