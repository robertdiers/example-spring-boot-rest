package com.twodigits.springbootrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

@SpringBootApplication
public class SpringbootrestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootrestApplication.class, args);

		int mb = 1024 * 1024;
		MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
		long xmx = memoryBean.getHeapMemoryUsage().getMax() / mb;
		long xms = memoryBean.getHeapMemoryUsage().getInit() / mb;
		System.out.println("Initial Memory (xms) : "+xms+"mb");
		System.out.println("Max Memory (xmx) : "+xmx+"mb");

	}

}
