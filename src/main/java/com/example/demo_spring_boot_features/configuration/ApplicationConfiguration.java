package com.example.demo_spring_boot_features.configuration;

import com.example.demo_spring_boot_features.component.ParentBean;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
//@PropertySource("classpath:application.properties")
@RequiredArgsConstructor
public class ApplicationConfiguration {

    private final ParentBean parentBean;


    /*@Value("${db.driverClassName}")
    private String driverClassName;
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;*/

    @Bean
    public List<ParentBean> parentBeans() {
        return List.of(parentBean);
    }
    /*!The propertySourcesPlaceholderConfigurer bean declaration is done using a static method.
     The reason for this is so that the bean declaration is picked up when the context is created,
     earlier than the configuration class annotated with @Configuration, and so the property values
     are added to the Spring environment and become available for injection in the said configuration
     class, before this class is initialized.*/

    /*Method name: The @Bean annotation together with the method are treated as a bean definition,
     and the method name becomes the bean id, so be careful with the method naming. */


    /*@Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public SimpleBean simpleBean() {
        return new SimpleBeanImpl();
    }

    @Bean
    public DataSource dataSource() throws SQLException {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(driverClassName);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }*/

   /* @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        var dataSource = new SimpleDriverDataSource();
        try {
            dataSource.setDriverClass((Class<? extends Driver>) Class.forName("com.mysql.cj.jdbc.Driver"));
            return dataSource;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }*/
}
