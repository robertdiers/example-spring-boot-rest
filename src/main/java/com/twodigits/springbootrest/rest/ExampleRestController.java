package com.twodigits.springbootrest.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.UUID;

import com.twodigits.springbootrest.reporting.ExampleReportingService;

@RestController
@Slf4j
public class ExampleRestController { 
    
    @Autowired
    private ExampleReportingService exampleReportingService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(@RequestParam(value="name", defaultValue="Hello World") String name) {
        return new Date() + " - " + name;
    }

    @RequestMapping(value = "/sendevent", method = RequestMethod.POST)
    public EventResultTO sendevent(@RequestBody ReportTO reportIn) throws InterruptedException {

        //generate ID
        String reportid = UUID.randomUUID().toString();        

        //async report generation
        exampleReportingService.generateReport(reportid, reportIn);

        log.info("##### /sendevent: " + reportid);

        //return id
        return new EventResultTO(reportid);
    }    

    @RequestMapping(value = "/getreport", method = RequestMethod.GET)
    public ReportTO getreport(@RequestParam(value="reportid") String reportid) {

        log.info("##### /getreport: " + reportid);

        //use id to get result
        return exampleReportingService.getReport(reportid);
    }    

}