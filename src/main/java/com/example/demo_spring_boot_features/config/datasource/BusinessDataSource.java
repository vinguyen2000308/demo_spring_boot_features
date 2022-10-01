package com.example.demo_spring_boot_features.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "ds1EntityManagerFactory",
        transactionManagerRef = "ds1TransactionManager",
        basePackages = "com.example.demo_spring_boot_features.repo"
)
@EnableTransactionManagement
public class BusinessDataSource {

    @Bean(name = "configBusinessDataSource")
    @ConfigurationProperties("business.datasource")
    public DataSourceProperties firstDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "businessDataSourceConf")
    public HikariDataSource firstDataSource(@Qualifier("configBusinessDataSource") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    @Qualifier("businessDataSourceTransManager")
    public LocalContainerEntityManagerFactoryBean ds1EntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                          @Qualifier("businessDataSourceConf") DataSource dataSource) {

        return builder
                .dataSource(dataSource)
                .packages(packagesToScan())
                .persistenceUnit("ds1-pu")
                .properties(hibernateProperties())
                .build();
    }

    protected String[] packagesToScan() {
        return new String[]{
                "com.example.demo_spring_boot_features.domain.model"
        };
    }

    protected Map<String, String> hibernateProperties() {
        return new HashMap<>() {
            {
                put("hibernate.dialect",
                        "org.hibernate.dialect.MariaDB103Dialect");
            }
        };
    }

}
