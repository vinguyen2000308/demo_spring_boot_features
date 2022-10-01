package com.example.demo_spring_boot_features.repo.jdbc;


import com.example.demo_spring_boot_features.domain.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

@Component
@Scope("prototype")
public class ForkingComponent extends RecursiveAction {

    private final List<Author> data;
    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private int batchSize;
    @Autowired
    private JoiningComponent joiningComponent;
    @Autowired
    private ApplicationContext applicationContext;

    public ForkingComponent(List<Author> data) {
        this.data = data;
    }

    @Override
    protected void compute() {
        if (data.size() > batchSize)
            ForkJoinTask.invokeAll(createSubtasks());
        else
            joiningComponent.executeBatch(data);
    }

    /*Therefore, the number of connections should be the same as or more than the number of
     fork/join threads that will execute batches.*/
    private List<ForkingComponent> createSubtasks() {
        List<ForkingComponent> subtasks = new ArrayList<>();
        int size = data.size();
        List<Author> jsonListOne = data.subList(0, (size + 1) / 2);
        List<Author> jsonListTwo = data.subList((size + 1) / 2, size);
        subtasks.add(applicationContext.getBean(ForkingComponent.class, new ArrayList<>(jsonListOne)));
        subtasks.add(applicationContext.getBean(ForkingComponent.class, new ArrayList<>(jsonListTwo)));
        return subtasks;
    }
}
