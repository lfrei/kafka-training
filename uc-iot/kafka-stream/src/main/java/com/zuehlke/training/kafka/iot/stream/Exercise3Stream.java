package com.zuehlke.training.kafka.iot.stream;

import com.zuehlke.training.kafka.iot.SensorMeasurement;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class Exercise3Stream {

    @Bean
    public KStream<String, SensorMeasurement> exercise3(StreamsBuilder builder) {

        GlobalKTable<String, String> metadata = builder.globalTable("metadata",
                Consumed.with(
                        Serdes.String(),
                        Serdes.String()
                )
        );

        KStream<String, SensorMeasurement> stream = builder.stream("myPlant");

        // TODO: add metadata to sensor message

        return stream;
    }
}
