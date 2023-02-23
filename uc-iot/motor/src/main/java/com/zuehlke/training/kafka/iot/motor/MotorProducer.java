package com.zuehlke.training.kafka.iot.motor;

import com.zuehlke.training.kafka.iot.SensorMeasurement;
import com.zuehlke.training.kafka.iot.motor.config.MotorConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
@RequiredArgsConstructor
public class MotorProducer {

    private final MotorConfig motorConfig;
    private final KafkaTemplate<String, SensorMeasurement> kafkaTemplate;

    @Scheduled(fixedDelayString = "${random.int(${motor.max-interval-ms})}")
    public void sendMeasurement() {
        SensorMeasurement measurement = getMeasurement();

        log.info("Sending measurement of {} for motor {} to topic {}",
                measurement,
                motorConfig.getMotorId(),
                motorConfig.getPlantId()
        );

        kafkaTemplate.send(
                motorConfig.getPlantId(),
                motorConfig.getMotorId(),
                measurement
        );
    }

    private SensorMeasurement getMeasurement() {
        return new SensorMeasurement(motorConfig.getMotorId(), System.currentTimeMillis(), null, getMotorState());
    }

    private String getMotorState() {
        List<String> states = motorConfig.getStates();
        return states.get(ThreadLocalRandom.current().nextInt(states.size()));
    }
}
