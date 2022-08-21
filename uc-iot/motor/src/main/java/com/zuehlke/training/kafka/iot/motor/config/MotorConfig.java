package com.zuehlke.training.kafka.iot.motor.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties("motor")
public class MotorConfig {
    String plantId;
    String motorId;
    Long maxIntervalMs;
    List<String> states;
}
