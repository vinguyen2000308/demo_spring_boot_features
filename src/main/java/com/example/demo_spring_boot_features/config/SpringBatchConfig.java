package com.example.demo_spring_boot_features.config;


import com.example.demo_spring_boot_features.domain.model.ModelTest1;
import com.example.demo_spring_boot_features.job_listener.Model1JobListener;
import com.example.demo_spring_boot_features.job_validator.CustomJobValidator;
import com.example.demo_spring_boot_features.repo.ModelTest1Repo;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor

/*Configuring a Job */
// Restartability
/*
 * - If JobExecution already exist for particular JobInstance -> restart
 * - Job should never be restarted, but should always be run as part of new JobInstance => set preventRestart = false
 * */
// Intercepting Job Execution
// JobParametersValidator

/*Configuring a Step*/

public class SpringBatchConfig {

    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;
    private ModelTest1Repo modelTest1Repo;

/*
    @Bean
    public BatchConfigurer batchConfigurer(@Qualifier(value = "batchingDataSourceConf") DataSource dataSource) {
        return new DefaultBatchConfigurer(dataSource) {
            @Override
            public PlatformTransactionManager getTransactionManager() {
                return new HibernateTransactionManager();
            }

            @Override
            protected JobRepository createJobRepository() throws Exception {
                JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
                factory.setDataSource(dataSource);
                factory.setTransactionManager(getTransactionManager());
                factory.setIsolationLevelForCreate("ISOLATION_SERIALIZABLE");
                factory.setTablePrefix("BATCH_");
                factory.setMaxVarCharLength(1000);
                return factory.getObject();
            }

            @Override
            protected JobLauncher createJobLauncher() throws Exception {
                SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
                jobLauncher.setJobRepository(createJobRepository());
                jobLauncher.afterPropertiesSet();
                return jobLauncher;
            }
        };
    }

    @Bean
    public FlatFileItemReader<ModelTest1> reader() {
        FlatFileItemReader<ModelTest1> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("src/main/resources/test1.csv"));
        itemReader.setName("csvReader");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }

    private LineMapper<ModelTest1> lineMapper() {
        DefaultLineMapper<ModelTest1> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("year", "sector", "type", "cfn_tle1", "cfn_tle2", "units", "magnitude", "source", "data_value", "flag");
        BeanWrapperFieldSetMapper<ModelTest1> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(ModelTest1.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;

    }


    @Bean
    public ModelTest1Processor processor() {
        return new ModelTest1Processor();
    }

    @Bean
    public RepositoryItemWriter<ModelTest1> writer() {
        RepositoryItemWriter<ModelTest1> writer = new RepositoryItemWriter<>();
        writer.setRepository(modelTest1Repo);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("csv-step").<ModelTest1, ModelTest1>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    @Qualifier(value = "testModel1")
    public Job runJob() {
        return jobBuilderFactory.get("importModel1")
                .listener(model1JobListener())
                .validator(customJobValidator())
                .flow(step1())
                .build()
                .build();

    }

    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(10);
        return asyncTaskExecutor;
    }


    @Bean
    public Model1JobListener model1JobListener() {
        return new Model1JobListener();
    }

    @Bean
    public CustomJobValidator customJobValidator() {
        return new CustomJobValidator();
    }*/
}
