package com.zuehlke.training.kafka.iot.stream;

import com.zuehlke.training.kafka.iot.SensorMeasurement;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class Exercise0Stream {

    @Bean
    public KStream<String, SensorMeasurement> example(StreamsBuilder builder) {

        KStream<String, SensorMeasurement> stream = builder.stream("myPlant");

        //TODO: try out the different kafka streams operations

        stream
                .filter((key, value) -> value != null)
                .mapValues(SensorMeasurement::getValue)
                .peek((key, value) -> log.info("Processing message with key={} and value={}", key, value));

        return stream;
    }
}
