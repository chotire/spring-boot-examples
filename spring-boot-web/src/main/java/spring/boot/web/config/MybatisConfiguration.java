package spring.boot.web.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import spring.boot.web.model.Domain;

@MapperScan(basePackages = {"spring.boot.**.repository"}, annotationClass = Repository.class)
public class MybatisConfiguration extends MybatisAutoConfiguration {
    @Autowired
    private MybatisProperties properties;
    
    @Bean
    @Override
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactory factory = super.sqlSessionFactory(dataSource);
        Configuration configuration = factory.getConfiguration();
        configuration.getTypeAliasRegistry().registerAliases(this.properties.getTypeAliasesPackage(), Domain.class);
        return factory;
    }
}