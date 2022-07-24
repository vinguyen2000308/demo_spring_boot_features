package com.example.demo_spring_boot_features.service;

import com.example.demo_spring_boot_features.component.ParentBean;
import com.example.demo_spring_boot_features.configuration.ApplicationConfiguration;
import com.example.demo_spring_boot_features.configuration.ApplicationConverterConfiguration;
import com.example.demo_spring_boot_features.converter.StringToLocalDateConverter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

/*
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class,
        ParentBean.class,
        StringToLocalDateConverter.class,
        ApplicationConverterConfiguration.class})
*/

/* @ExtendWith and @ContextConfiguration = @SpringJunitConfig*/
@SpringJUnitConfig(classes = {
        ApplicationConfiguration.class,
        ParentBean.class,
        StringToLocalDateConverter.class,
        ApplicationConverterConfiguration.class
})
@Slf4j
public class CheckingInfoServiceTest {


    @Autowired
    private List<ParentBean> parentBeans;

    @BeforeEach
    public void setup() {
        Assertions.assertNotNull(parentBeans);
        Assertions.assertEquals(1L, parentBeans.size());
    }

    @Test
    public void shouldGetTheFamilyName() {
        String familyName = parentBeans.get(0).getFamilyName();
        Assertions.assertEquals("Smith", familyName);
        log.info(familyName);
    }
}