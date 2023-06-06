package com.example.batchwang.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class inMemeItemProcessor implements ItemProcessor<Integer,Integer> {
    @Override
    public Integer process(Integer item) throws Exception {
        return Integer.sum(10,item);
    }
}
