package com.excel.service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/import")
public class ImportController {

    private final JobLauncher jobLauncher;
    private final Job job;

    @PostMapping
    public String importExcel() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addDate("runAt", new Date())
                .toJobParameters();

        JobExecution execution = jobLauncher.run(job, params);
        return "Job Status: " + execution.getStatus();
    }
}
