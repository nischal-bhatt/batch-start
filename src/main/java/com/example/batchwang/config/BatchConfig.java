package com.example.batchwang.config;

import com.example.batchwang.processor.inMemeItemProcessor;
import com.example.batchwang.reader.ItemReader;
import com.example.batchwang.writer.ConsoleItemWriter;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.Console;

@EnableBatchProcessing
@Configuration
public class BatchConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobExecutionListener jobExecutionListener;

    @Autowired
    private StepExecutionListener stepExecutionListener;

    @Autowired
    private inMemeItemProcessor InMemeItemProcessor;

    @Bean
    public Step step1(){
        return stepBuilderFactory.get("step")
                .listener(stepExecutionListener)
                .tasklet(hello())
                .build();
    }

    @Bean
    public Step step2(){
        return stepBuilderFactory.get("step2")
                .listener(stepExecutionListener)
                .tasklet(hello2())
                .build();
    }

    private Tasklet hello2() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("test2");
                return RepeatStatus.FINISHED;
            }
        };
    }

    private Tasklet hello() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("test");
                return RepeatStatus.FINISHED;
            }
        };
    }

    @Bean
    public Job helloJob(){
        return jobBuilderFactory.get("test")
                .start(step1())
                .next(step2())
                .next(step3())
                .listener(jobExecutionListener)
                .build();
    }


    @Bean
     public ItemReader reader(){
       return new ItemReader();
     }




    public Step step3()
    {
        return stepBuilderFactory.get("step3")
                .<Integer,Integer>chunk(3).reader(reader())
                .processor(InMemeItemProcessor)
                .writer(new ConsoleItemWriter())
                .build();
    }
}
