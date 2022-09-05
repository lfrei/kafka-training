package com.zuehlke.training.kafka.iot.stream;

import com.zuehlke.training.kafka.iot.SensorMeasurement;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class Exercise3Stream {

    @Bean
    public KStream<String, SensorMeasurement> exercise3(StreamsBuilder builder) {

        KStream<String, SensorMeasurement> stream = builder.stream("myPlant");

        // TODO: group messages for the same sensor (= key)

        // TODO: perform a windowed aggregation with a timeframe of 1min

        // TODO: calculate the average value from the aggregated values

        // TODO: write the result to a new Kafka Topic

        return stream;
    }
}
