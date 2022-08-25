package com.zuehlke.training.kafka.iot.stream;

import com.zuehlke.training.kafka.iot.SensorMeasurement;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ExampleStream {

    @Bean
    public KStream<String, SensorMeasurement> example(StreamsBuilder builder) {

        KStream<String, SensorMeasurement> stream = builder.stream("myPlant");

        stream
                .filter((key, value) -> value != null)
                .peek((key, value) -> log.info("Processing message with key={} and value={}", key, value))
                .to("myPlant-processed");

        return stream;
    }
}
