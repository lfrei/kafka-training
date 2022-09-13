package com.zuehlke.training.kafka.witnessprotection.stream;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class Exercise2Stream {

	@Bean
	public KStream<String, String> exercise2(StreamsBuilder builder) {

		KStream<String, String> sourceStream = builder.stream("mysql-01-events");
		//Implement here
		return sourceStream;
	}

}
