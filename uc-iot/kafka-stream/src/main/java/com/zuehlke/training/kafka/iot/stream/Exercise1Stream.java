package com.zuehlke.training.kafka.iot.stream;

import com.zuehlke.training.kafka.iot.SensorMeasurement;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Branched;
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

    // Solution including the 'stretch goal' of the exercise:
    @Bean
    public KStream<String, SensorMeasurement> exercise1_extended(StreamsBuilder builder) {

        KStream<String, SensorMeasurement> stream = builder.stream("myPlant");

        // There are many possible ways to solve this. You could also solve this, by merging the streams
        // after filtering the values and before sending the messages to the alert topic. Or you could create
        // a separate stream for motors and sensors. Or implement a filter that considers the key as well. Or...

        stream
                .split()
                .branch((key, value) -> key.equals("mySensor"),
                        Branched.withConsumer(s -> s
                                .filter(new HighMeasurement())
                                .to("myPlant-alert-extended")))
                .branch((key, value) -> key.equals("myMotor"),
                        Branched.withConsumer(s -> s
                                .filter(new ErrorMeasurement())
                                .to("myPlant-alert-extended")));

        return stream;
    }

    private static class HighMeasurement implements Predicate<String, SensorMeasurement> {

        @Override
        public boolean test(String key, SensorMeasurement value) {
            return (Long) value.getValue() > 800_000L;
        }
    }

    private static class ErrorMeasurement implements Predicate<String, SensorMeasurement> {

        @Override
        public boolean test(String key, SensorMeasurement value) {
            return "error".equals(value.getValue().toString());
        }
    }
}
