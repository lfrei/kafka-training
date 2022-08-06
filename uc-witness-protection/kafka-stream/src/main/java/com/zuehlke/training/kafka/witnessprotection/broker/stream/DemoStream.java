package com.zuehlke.training.kafka.witnessprotection.broker.stream;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoStream {

	private static final Logger LOG = LoggerFactory.getLogger(DemoStream.class);

	private ObjectMapper objectMapper = new ObjectMapper();

	private boolean isStatA = false;

	@Bean
	public KStream<String, String> kstreamPromotionUppercase(StreamsBuilder builder) {
		var stringSerde = Serdes.String();

		KStream<String, String> sourceStream = builder.stream("sourceTopic",
				Consumed.with(stringSerde, stringSerde));

		KStream<String, String> stataStream = sourceStream.filter(this::filterInputForTopic);
		stataStream.to("StatA");

		return sourceStream;
	}




	private boolean filterInputForTopic(String key, String value) {
		String[] parts = value.split(",");
		String laufNummer = parts[6].substring(18,19);

		if (Integer.parseInt(laufNummer) > 2) {
			return true;
		}else {
			return false;
		}
	}
}
