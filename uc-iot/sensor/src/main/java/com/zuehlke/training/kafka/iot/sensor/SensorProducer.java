package com.zuehlke.training.kafka.iot.sensor;

import com.zuehlke.training.kafka.iot.sensor.config.SensorConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
@RequiredArgsConstructor
public class SensorProducer {

    private final SensorConfig sensorConfig;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(fixedDelay = 10000)
    public void sendMeasurement() {
        String measurement = getMeasurement();

        log.info("Sending measurement of {} for sensor {} to topic {}",
                measurement,
                sensorConfig.getSensorId(),
                sensorConfig.getPlantId()
        );

        kafkaTemplate.send(
                sensorConfig.getPlantId(),
                sensorConfig.getSensorId(),
                measurement
        );
    }

    private String getMeasurement() {
        Long min = sensorConfig.getMinValue();
        Long max = sensorConfig.getMaxValue();

        return String.valueOf(ThreadLocalRandom.current().nextLong(max - min) + min);
    }
}
