package com.example.demo_spring_boot_features;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@RequiredArgsConstructor
@Slf4j
public class DemoSpringBootFeaturesApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(DemoSpringBootFeaturesApplication.class, args);
    }


    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Override
    public void run(String... args) throws Exception {

    }
}
