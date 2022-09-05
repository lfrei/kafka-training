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
    public KStream<String, SensorMeasurement> exercise1(StreamsBuilder builder) {

        KStream<String, SensorMeasurement> stream = builder.stream("myPlant");

        // TODO: write alerts for high measurement values to a new topic

        stream
                // TODO: filter by key to only get sensor values
                .filter(((key, value) -> false))
                // TODO: filter by values to only keep the sensor measurement of the top 20%
                .filter(((key, value) -> false))
                .to("myPlant-alert");

        return stream;
    }
}
