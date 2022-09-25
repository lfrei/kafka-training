package com.zuehlke.training.kafka.witnessprotection.stream;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class Exercise4Stream {

	@Bean
	public KStream<String, String> exercise4(StreamsBuilder builder) {
		// TODO
		return sourceStream;
	}

}
