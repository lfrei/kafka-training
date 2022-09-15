package com.zuehlke.training.kafka.iot.stream;

import com.zuehlke.training.kafka.iot.SensorMeasurement;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.apache.kafka.streams.state.Stores;
import org.apache.kafka.streams.state.WindowBytesStoreSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@Slf4j
public class Exercise3Stream {

    @Bean
    public KStream<String, SensorMeasurement> exercise3(StreamsBuilder builder) {

        KStream<String, SensorMeasurement> stream = builder.stream("myPlant");

        WindowBytesStoreSupplier store = Stores.persistentWindowStore(
                "mySensorAggregateStore",
                Duration.ofMinutes(1),
                Duration.ofMinutes(1),
                false
        );

        stream
                .filter(((key, value) -> key.equals("mySensor")))
                .groupByKey()
                .windowedBy(TimeWindows.ofSizeWithNoGrace(Duration.ofMinutes(1)))
                .aggregate(
                        () -> 0L,
                        (key, value, aggregate) -> aggregate + (Long) value.getValue(),
                        Materialized.<String, Long>as(store)
                                .withKeySerde(Serdes.String())
                                .withValueSerde(Serdes.Long())
                )
                .mapValues(
                        (readOnlyKey, value) ->
                                SensorMeasurement.newBuilder()
                                        .setDatetime(System.currentTimeMillis())
                                        .setSensorId(readOnlyKey.key())
                                        .setValue(value / 6)
                                        .build()

                )
                .toStream()
                .to("myPlant-avg");

        return stream;
    }
}
