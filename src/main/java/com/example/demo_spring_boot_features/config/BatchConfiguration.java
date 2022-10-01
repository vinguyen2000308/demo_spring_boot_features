package com.example.demo_spring_boot_features.config;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class BatchConfiguration extends DefaultBatchConfigurer {


    @Override
    public void setDataSource(@Qualifier("") DataSource dataSource) {
        super.setDataSource(dataSource);
    }
}
