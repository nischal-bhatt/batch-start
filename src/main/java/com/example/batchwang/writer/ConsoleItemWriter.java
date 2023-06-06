package com.example.batchwang.writer;

import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.AbstractItemStreamItemReader;
import org.springframework.batch.item.support.AbstractItemStreamItemWriter;

import java.util.List;

public class ConsoleItemWriter extends AbstractItemStreamItemWriter<Integer> {

    @Override
    public void write(List<? extends Integer> list) throws Exception {
        list.stream().forEach(System.out::println);
        System.out.println("writing each chuynk");
    }
}
