package com.example.demo_spring_boot_features.job_listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class Model1JobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Before Running This Job " + jobExecution.getJobId());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("After Running This Job " + jobExecution.getJobId());
    }
}
