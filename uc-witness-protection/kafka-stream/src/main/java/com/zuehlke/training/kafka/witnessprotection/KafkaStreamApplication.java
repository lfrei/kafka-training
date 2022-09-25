package com.zuehlke.training.kafka.witnessprotection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableKafkaStreams
public class KafkaStreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaStreamApplication.class, args);
	}

}