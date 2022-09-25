package com.zuehlke.training.kafka.witnessprotection.stream;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class Exercise3Stream {

    @Bean
    public KStream<String, String> exercise3(StreamsBuilder builder) {

        KStream<String, String> sourceStream = builder.stream("mysql-01-events");
        KStream<String, String> stataStream = sourceStream.filter(this::filterInputForTopic);
        stataStream.to("tax-department");
        return sourceStream;
    }

    private boolean filterInputForTopic(String key, String value) {
        if (value.contains("<eCH-0020:moveIn>")) {
            return true;
        }else {
            return false;

        }
    }
}