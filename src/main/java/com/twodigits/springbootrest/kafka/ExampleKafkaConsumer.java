package com.twodigits.springbootrest.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twodigits.springbootrest.jdbc.ExampleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ExampleKafkaConsumer {   
    
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ExampleRepository exampleRepository;

    @KafkaListener(topics = "mytopic1", groupId = "examplegroupid")
    public void exampleLIstener(String message) throws JsonMappingException, JsonProcessingException {
        log.info("##### received message in group examplegroupid: " + message);

        //store data in postgre
        KafkaMessageTO mes = objectMapper.readValue(message, KafkaMessageTO.class);
        exampleRepository.insert(mes.getTestid(), mes.getContent());

        log.info("##### data stored: " + message);
    }
    
}