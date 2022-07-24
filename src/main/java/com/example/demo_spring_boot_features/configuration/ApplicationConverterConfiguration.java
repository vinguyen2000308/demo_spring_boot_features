package com.example.demo_spring_boot_features.configuration;

import com.example.demo_spring_boot_features.converter.StringToLocalDateConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class ApplicationConverterConfiguration {


    private final StringToLocalDateConverter stringToLocalDate;

    @Bean
    ConversionServiceFactoryBean conversionServiceFactoryBean() {
        ConversionServiceFactoryBean factory = new ConversionServiceFactoryBean();
        factory.setConverters(Set.of(stringToLocalDate));
        return factory;
    }

    @Bean
    ConversionService conversionService(ConversionServiceFactoryBean factory) {
        return factory.getObject();
    }
}
