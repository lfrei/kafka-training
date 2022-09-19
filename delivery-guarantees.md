# Delivery Guarantees

[⬅️ Back to Kafka overview](README.md)

Configure delivery guarantees for your IoT use case application.

## Examine processing guarantee

Open the [kafka-streams](uc-iot/kafka-stream) Spring Boot Application from the IoT use case in an IDE (e.g. Visual Studio Code or Intellij).

Start the application and have a look at the StreamsConfig output:

```
org.apache.kafka.streams.StreamsConfig   : StreamsConfig values: 
...
bootstrap.servers = [localhost:9092]
processing.guarantee = ?
```

📝 Can you see what the default processing guarantee is? What does it mean?

## Configure processing guarantee

Configure the `processing.guarantee` property in the [application.yaml](uc-iot/kafka-stream/src/main/resources/application.yaml) file of the stream application:

```
spring:
  kafka:
    streams:
      properties:
        processing.guarantee: <value>
```

💡 Have a look at the different options in the Kafka Streams [documentation](https://kafka.apache.org/32/documentation/streams/developer-guide/config-streams.html#processing-guarantee)

📝 Configure an "exactly once" processing. Why are there multiple ways to do this?
