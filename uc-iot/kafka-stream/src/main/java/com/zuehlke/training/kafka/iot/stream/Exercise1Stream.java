package com.zuehlke.training.kafka.iot.stream;

import com.zuehlke.training.kafka.iot.SensorMeasurement;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class Exercise1Stream {

    @Bean
    public KStream<String, SensorMeasurement> samples(StreamsBuilder builder) {

        KStream<String, SensorMeasurement> stream = builder.stream("myPlant");

        // TODO: calculate the average value of a sensor over a 1min time frame

        return stream;
    }
}
