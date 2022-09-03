package com.zuehlke.training.kafka.iot.stream;

import com.zuehlke.training.kafka.iot.SensorMeasurement;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.test.TestRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

import static io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG;
import static org.assertj.core.api.Assertions.assertThat;

class Exercise1StreamTest {

    private Serde<String> keySerde;
    private Serde<SensorMeasurement> valueSerde;
    private TopologyTestDriver topologyTestDriver;
    private TestInputTopic<String, SensorMeasurement> inputTopic;
    private TestOutputTopic<String, SensorMeasurement> outputTopic;

    @BeforeEach
    void setup() {
        keySerde = Serdes.String();
        valueSerde = new SpecificAvroSerde<>();
        valueSerde.configure(Collections.singletonMap(
                "schema.registry.url", "mock://localhost:8081"), false);

        StreamsBuilder streamsBuilder = new StreamsBuilder();
        Exercise1Stream exercise1Stream = new Exercise1Stream();

        exercise1Stream.exercise1(streamsBuilder);

        topologyTestDriver = new TopologyTestDriver(streamsBuilder.build(), getKafkaStreamProperties());

        inputTopic = topologyTestDriver.createInputTopic("myPlant",
                keySerde.serializer(),
                valueSerde.serializer());

        outputTopic = topologyTestDriver.createOutputTopic("myPlant-alert",
                keySerde.deserializer(),
                valueSerde.deserializer());
    }

    @AfterEach
    void tearDown() {
        topologyTestDriver.close();
        keySerde.close();
        valueSerde.close();
    }

    @Test
    void exercise1_messagesProcessed_top20PercentForwarded() {
        inputTopic.pipeInput(createRecord("mySensor", 10_000L));
        inputTopic.pipeInput(createRecord("mySensor", 100_000L));
        inputTopic.pipeInput(createRecord("mySensor", 999_999L));
        inputTopic.pipeInput(createRecord("mySensor", 888_888L));

        List<TestRecord<String, SensorMeasurement>> outputMessages = outputTopic.readRecordsToList();

        assertThat(outputMessages).hasSize(2);
        assertThat(outputMessages).extracting(TestRecord::key).containsOnly("mySensor");
        assertThat(outputMessages)
                .extracting(TestRecord::value)
                .extracting(SensorMeasurement::getValue)
                .containsOnly(999_999L, 888_888L);
    }

    @Test
    void exercise1_messagesProcessed_motorsIgnored() {
        inputTopic.pipeInput(createRecord("mySensor", 10_000L));
        inputTopic.pipeInput(createRecord("myMotor", "running"));

        List<TestRecord<String, SensorMeasurement>> outputMessages = outputTopic.readRecordsToList();

        assertThat(outputMessages).isEmpty();
    }

    private TestRecord<String, SensorMeasurement> createRecord(String key, Object value) {
        SensorMeasurement sensorMeasurement = SensorMeasurement.newBuilder()
                .setSensorId(key)
                .setDatetime(System.currentTimeMillis())
                .setValue(value)
                .build();

        return new TestRecord<>(key, sensorMeasurement);
    }

    private static Properties getKafkaStreamProperties() {
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "my-test");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, "org.apache.kafka.common.serialization.Serdes$StringSerde");
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, "io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde");
        properties.put(SCHEMA_REGISTRY_URL_CONFIG, "mock://localhost:8081");
        return properties;
    }
}