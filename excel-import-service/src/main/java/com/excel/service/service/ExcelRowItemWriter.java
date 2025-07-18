package com.excel.service.service;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.excel.service.model.ExcelRow;
import com.excel.service.repository.ExcelRowRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ExcelRowItemWriter implements ItemWriter<ExcelRow> {

    private final ExcelRowRepository repository;

    @Override
    public void write(Chunk<? extends ExcelRow> chunk) throws Exception {
        repository.saveAll(chunk.getItems()); 
    }
}
