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
public class Exercise2Stream {

    @Bean
    public KStream<String, SensorMeasurement> exercise2(StreamsBuilder builder) {

        GlobalKTable<String, String> metadata = builder.globalTable("metadata",
                Consumed.with(
                        Serdes.String(),
                        Serdes.String()
                )
        );

        KStream<String, SensorMeasurement> stream = builder.stream("myPlant");

        // TODO: join the myPlant stream with the metadata table using the keys

        // TODO: expand SensorMeasurement value with type from metadata

        // TODO: write the result to a new Kafka Topic

        return stream;
    }
}
