package com.example.demo_spring_boot_features.config;

import com.example.demo_spring_boot_features.domain.model.ModelTest1;
import org.springframework.batch.item.ItemProcessor;

public class ModelTest1Processor implements ItemProcessor<ModelTest1, ModelTest1> {
    @Override
    public ModelTest1 process(ModelTest1 modelTest1) throws Exception {
        return modelTest1;
    }
}
