package com.zuehlke.training.kafka.iot.stream;

import com.zuehlke.training.kafka.iot.SensorMeasurement;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class Exercise1Stream {

    @Bean
    public KStream<String, SensorMeasurement> exercise1(StreamsBuilder builder) {

        KStream<String, SensorMeasurement> stream = builder.stream("myPlant");

        stream
                .filter(((key, value) -> key.equals("mySensor")))
                .filter(new HighMeasurement())
                .to("myPlant-alert");

        return stream;
    }

    private static class HighMeasurement implements Predicate<String, SensorMeasurement> {

        @Override
        public boolean test(String key, SensorMeasurement value) {
            return (Long) value.getValue() > 800_000L;
        }
    }
}
