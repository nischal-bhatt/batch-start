package com.example.batchwang.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.stereotype.Component;

@Component
public class JobExecutionListener implements org.springframework.batch.core.JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("before starting the job " + jobExecution.getJobInstance().getJobName());
        System.out.println("before starting the job " + jobExecution.getExecutionContext().toString());
        jobExecution.getExecutionContext().put("my name","michael");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

        System.out.println("job ended" + jobExecution.getExecutionContext().toString());
    }
}
