package com.twodigits.springbootrest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class ExampleRestController {

    @RequestMapping("/hello")
    public String hello(@RequestParam(value="name", defaultValue="Hello World") String name) {
        return new Date() + " - " + name;
    }

}
