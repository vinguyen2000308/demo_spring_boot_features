package com.example.demo_spring_boot_features;

import com.example.demo_spring_boot_features.component.ChildBean;
import com.example.demo_spring_boot_features.component.ParentBean;
import com.example.demo_spring_boot_features.component.SimpleBean;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.List;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@RequiredArgsConstructor
public class DemoSpringBootFeaturesApplication implements CommandLineRunner {


    private final ApplicationContext applicationContext;

    private final SimpleBean simpleBean;

    private final ParentBean parentBean;

    private final ChildBean childBean;

    private final List<ParentBean> parentBeans;

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringBootFeaturesApplication.class, args);
    }

    @Override
    public void run(String... args) {

        injectingDependenciesThatAreNotBean();
    }

    private void listOfBean() {
        parentBeans.forEach(System.out::println);
    }

    private void injectingDependenciesThatAreNotBean() {
        listOfBean();
    }

    private void setUpStringToLocalDateConverter() {
        System.out.println("String to local date converter: ");
        System.out.println("    " + parentBean.getBirthDay());
    }

    private void beanDeclarationInheritance() {
        System.out.println("Parent Bean: ");
        System.out.println("    " + parentBean);
        System.out.println("Child Bean: ");
        System.out.println("    " + childBean);
    }
}
