package com.example.demo_spring_boot_features.config;


import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "businessEntityManagerFactory",
        transactionManagerRef = "businessTransactionManager",
        basePackages = "com.example.demo_spring_boot_features.repo.app2"
)
@EnableTransactionManagement
@Slf4j
public class TestDatabaseDataSourceConf {

    @Bean("businessDataSourceProperties")
    @ConfigurationProperties("app2.datasource")
    public DataSourceProperties businessDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean("businessDataSource")
    public HikariDataSource businessDataSource(@Qualifier("businessDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean businessEntityManagerFactory
            (@Qualifier("testDataBaseEntityManagerFactoryBuilder") EntityManagerFactoryBuilder entityManagerFactoryBuilder, @Qualifier("businessDataSource") DataSource dataSource) {
        return entityManagerFactoryBuilder
                .dataSource(dataSource)
                .packages(packagesToScan())
                .persistenceUnit("business-pu")
                .properties(hibernateProperties())
                .build();
    }


    @Bean("testDataBaseEntityManagerFactoryBuilder")
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), hibernateProperties() ,null);
    }

    @Bean
    public PlatformTransactionManager businessTransactionManager(
            @Qualifier("businessEntityManagerFactory")
            EntityManagerFactory businessEntityManagerFactory) {
        return new JpaTransactionManager(businessEntityManagerFactory);
    }

    protected String[] packagesToScan() {
        return new String[]{
                "com.example.demo_spring_boot_features.domain.app2.model",
                "com.example.demo_spring_boot_features.repo.app2"
        };
    }

    protected Map<String, String> hibernateProperties() {
        return new HashMap<>() {
            {
                put("hibernate.dialect", "org.hibernate.dialect.MariaDB103Dialect");
            }
        };
    }
}
