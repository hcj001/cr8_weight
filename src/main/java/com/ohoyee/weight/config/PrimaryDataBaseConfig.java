package com.ohoyee.weight.config;


import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

@Data
@Configuration
// 前缀为primary.datasource.druid的配置信息
@ConfigurationProperties(prefix = "primary.datasource.druid")
@MapperScan(basePackages = PrimaryDataBaseConfig.PACKAGE, sqlSessionFactoryRef = "primarySqlSessionFactory")
public class PrimaryDataBaseConfig extends BaseConfig{
    /**
     * dao层的包路径
     */
    static final String PACKAGE = "com.ohoyee.weight.mapper";

    /**
     * mapper文件的相对路径
     */
    private static final String MAPPER_LOCATION = "classpath:mybatis/primary/*.xml";

    /**
     * config配置文件路径
     */
//    private static final String MYBATIS_CONFIG = "classpath:mybatis/test1/mybatis-config.xml";
    @Primary
    @Bean(name = "primaryDataSource")
    public DataSource primaryDataSource() throws SQLException {
        DruidDataSource druid = new DruidDataSource();
        initDurid(druid);
        return druid;
    }

    // 创建该数据源的事务管理
    @Primary
    @Bean(name = "primaryTransactionManager")
    public DataSourceTransactionManager primaryTransactionManager() throws SQLException {
        return new DataSourceTransactionManager(primaryDataSource());
    }

    // 创建Mybatis的连接会话工厂实例
    @Primary
    @Bean(name = "primarySqlSessionFactory")
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("primaryDataSource") DataSource primaryDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(primaryDataSource);  // 设置数据源bean
//        sessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver()
//                .getResource(MYBATIS_CONFIG));
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(PrimaryDataBaseConfig.MAPPER_LOCATION));  // 设置mapper文件路径

        return sessionFactory.getObject();
    }
}
