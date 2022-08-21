package com.zuehlke.training.kafka.iot.sensor.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("sensor")
public class SensorConfig {
    String plantId;
    String sensorId;
    Long intervalMs;
    Long minValue;
    Long maxValue;
}
