package com.zuehlke.training.kafka.witnessprotection.stream;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Exercise2Stream {

	private static final Logger LOG = LoggerFactory.getLogger(Exercise2Stream.class);

	private ObjectMapper objectMapper = new ObjectMapper();

	private boolean isStatA = false;

	@Bean
	public KStream<String, String> exercise2(StreamsBuilder builder) {

		KStream<String, String> sourceStream = builder.stream("mysql-01-events");

		KStream<String, String> stataStream = sourceStream.filter(this::filterInputForTopic);
		stataStream.to("tax-department");

		return sourceStream;
	}




	private boolean filterInputForTopic(String key, String value) {

		if (value.contains("<eCH-0020:moveIn>")) {
			return true;
		}else {
			return false;
		}
	}
}
