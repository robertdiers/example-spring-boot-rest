package com.twodigits.springbootrest.jdbc;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ExampleRepository {

    private static final String TABLENAME = "testtable";
    private static final String[] SQLS = {
        "create table if not exists "+TABLENAME+" (testid VARCHAR(100) NOT NULL)",
        "alter table "+TABLENAME+" add if not exists content VARCHAR(1000)"
    };

    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void init() {
        for (String sql : SQLS) {
            try {
                log.info("execute: "+sql);
                jdbcTemplate.execute(sql);
            } catch (Exception e) {
                log.warn("warning preparing database", e);
            }
        }
    }

    public void insert(String key, String content) {
        String insert = "insert into "+TABLENAME+" (testid, content) VALUES (?, ?)";
        jdbcTemplate.update(insert, key, content);
    }
    
}