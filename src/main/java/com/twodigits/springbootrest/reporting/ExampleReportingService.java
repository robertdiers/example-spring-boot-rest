package com.twodigits.springbootrest.reporting;

import java.util.HashMap;
import java.util.Map;

import com.twodigits.springbootrest.rest.ReportTO;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ExampleReportingService {

    private Map<String,ReportTO> myIncredibleStupidStorage = new HashMap<>();

    @Async("asyncExecutor")
    public void generateReport(String reportid, ReportTO reportIn) throws InterruptedException {
        Thread.sleep(11000);  //force first check to fail, simulate report generation    
        int age = new java.util.Random().nextInt(100);
        reportIn.setAge(age);
        reportIn.setReportid(reportid);
        myIncredibleStupidStorage.put(reportid, reportIn);
        log.info("##### async - report saved: " + reportid);
    }

    public ReportTO getReport(String reportid) {
        return myIncredibleStupidStorage.get(reportid);
    }
    
}
