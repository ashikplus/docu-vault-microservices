package com.excel.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excel.service.model.ExcelRow;

public interface ExcelRowRepository extends JpaRepository<ExcelRow, Long> {
}
