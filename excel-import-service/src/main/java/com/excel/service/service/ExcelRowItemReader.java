package com.excel.service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import com.excel.service.model.ExcelRow;

import java.util.Iterator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ExcelRowItemReader implements ItemReader<ExcelRow> {

    private final S3ExcelReaderService s3ExcelReaderService;
    private Iterator<ExcelRow> rowIterator;
    private boolean initialized = false;

    @Override
    public ExcelRow read() throws Exception {
        if (!initialized) {
            List<ExcelRow> rows = s3ExcelReaderService.readExcel("your-excel.xlsx");
            rowIterator = rows.iterator();
            initialized = true;
        }
        return rowIterator.hasNext() ? rowIterator.next() : null;
    }
}
