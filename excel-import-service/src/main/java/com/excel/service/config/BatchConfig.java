package com.excel.service.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.excel.service.model.ExcelRow;
import com.excel.service.service.ExcelRowItemReader;
import com.excel.service.service.ExcelRowItemWriter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {

    @SuppressWarnings("removal")
	private final JobBuilderFactory jobBuilderFactory;
    @SuppressWarnings("removal")
	private final StepBuilderFactory stepBuilderFactory;
    private final ExcelRowItemReader reader;
    private final ExcelRowItemWriter writer;

    @SuppressWarnings({ "removal" })
	@Bean
    public Step step() {
        return stepBuilderFactory.get("excel-import-step")
                .<ExcelRow, ExcelRow>chunk(10)
                .reader(reader)
                .writer(writer)
                .build();
    }

    @Bean
    public Job job() {
        return jobBuilderFactory.get("excel-import-job")
                .start(step())
                .build();
    }
}
