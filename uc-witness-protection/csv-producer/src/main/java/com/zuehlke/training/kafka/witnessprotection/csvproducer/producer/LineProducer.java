package com.zuehlke.training.kafka.witnessprotection.csvproducer.producer;

import com.zuehlke.training.kafka.witnessprotection.csvproducer.io.LineMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class LineProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    private Logger logger = LoggerFactory.getLogger(LineProducer.class);

    public LineProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendMessage(LineMessage lineMessage) {
        try {
            kafkaTemplate.send("sourceTopic", objectMapper.writeValueAsString(lineMessage));
            logger.info("Line with id: {} sent", lineMessage.getId());
        } catch (JsonProcessingException e) {
            logger.error("Error while serializing " + lineMessage.toString(), e);
        }
    }

}
