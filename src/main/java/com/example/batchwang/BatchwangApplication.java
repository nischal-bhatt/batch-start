package com.example.batchwang;

import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;
import java.util.UUID;


@SpringBootApplication
@EnableBatchProcessing
public class BatchwangApplication implements CommandLineRunner {



	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	public static void main(String[] args) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

        //make sure to add the "edit configurations" and program params otherwise job wont run
		SpringApplication.run(BatchwangApplication.class, args);

	}


	@Override
	public void run(String... args) throws Exception {


		JobParameters jobParameters = new JobParametersBuilder().addString("key", UUID.randomUUID().toString()).toJobParameters();
		System.out.println(jobLauncher + " nish ");
		JobExecution execution = jobLauncher.run(job, jobParameters);
		System.out.println("STATUS :: " + execution.getStatus());
	}
}
