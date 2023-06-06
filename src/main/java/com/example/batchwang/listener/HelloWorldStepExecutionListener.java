package com.example.batchwang.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldStepExecutionListener implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("in step " + stepExecution.getJobExecution().getExecutionContext().toString());
        System.out.println("inside step" + stepExecution.getJobExecution().getJobParameters().getString("jobRunDate"));
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("in step " + stepExecution.getJobExecution().getExecutionContext().toString());
        return null;
    }
}
