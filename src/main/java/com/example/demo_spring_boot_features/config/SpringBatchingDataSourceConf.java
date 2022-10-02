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
        entityManagerFactoryRef = "springBatchingEntityManagerFactory",
        transactionManagerRef = "springBatchingTransactionManager",
        basePackages = "com.example.demo_spring_boot_features.repo.app1"
)
@EnableTransactionManagement
@Slf4j
public class SpringBatchingDataSourceConf {

    @Bean("springBatchingDsProperties")
    @ConfigurationProperties("app1.datasource")
    public DataSourceProperties springBatchingDsProperties() {
        return new DataSourceProperties();
    }

    @Bean("springBatchingDataSource")
    public HikariDataSource springBatchingDs(@Qualifier("springBatchingDsProperties") DataSourceProperties dataSourceProperties) {

        log.info("Spring Batching datasource properties {} ", dataSourceProperties.toString());
        return dataSourceProperties
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean("springBatchingEntityManagerFactoryBuilder")
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), hibernateProperties(), null);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean springBatchingEntityManagerFactory
            (@Qualifier("springBatchingEntityManagerFactoryBuilder") EntityManagerFactoryBuilder entityManagerFactoryBuilder, @Qualifier("springBatchingDataSource") DataSource dataSource) {
        return entityManagerFactoryBuilder
                .dataSource(dataSource)
                .packages(packagesToScan())
                .persistenceUnit("spring-batching-pu")
                .properties(hibernateProperties())
                .build();
    }

    @Bean
    public PlatformTransactionManager springBatchingTransactionManager(
            @Qualifier("springBatchingEntityManagerFactory")
            EntityManagerFactory springBatchingEntityManagerFactory) {
        return new JpaTransactionManager(springBatchingEntityManagerFactory);
    }

    protected String[] packagesToScan() {
        return new String[]{
                "com.example.demo_spring_boot_features.domain.app1.model",
                "com.example.demo_spring_boot_features.repo.app1"
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
