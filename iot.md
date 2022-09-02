# IoT

## Initial Setup

For the IoT use case, there are two types of containers that simulate IoT devices in a plant:

* A sensor, that sends measurements in a fixed interval
* A motor, that sends an update if its state changes

In the [Docker Compose](docker-compose.yml) one of each is preconfigured.
If you want to configure your own sensor / motor you have the following options:

### Configure a sensor

A sensor is sending random values in a fixed interval. You can specify the interval, as well as the min and max values.


| Property           | Description                                           |
|--------------------|-------------------------------------------------------|
| SENSOR_SENSOR_ID   | ID of the sensor. Key of the kafka message            |
| SENSOR_PLANT_ID    | ID of the plant, where the sensor is from. Topic Name |
| SENSOR_INTERVAL_MS | Interval duration in ms (default: 10000)              |
| SENSOR_MIN_VALUE   | Minimal value the sensor can generate                 |
| SENSOR_MAX_VALUE   | Maximal value the sensor can generate                 |

### Configure a motor

A motor is changing its state at a random time. You can specify the states, as well as the max interval the motor will wait until sending an update.

| Property              | Description                                          |
|-----------------------|------------------------------------------------------|
| MOTOR_MOTOR_ID        | ID of the motor. Key of the kafka message            |
| MOTOR_PLANT_ID        | ID of the plant, where the motor is from. Topic Name |
| MOTOR_MAX_INTERVAL_MS | Max Interval duration in ms (default: 30000)         |
| MOTOR_STATES_0        | State of the motor (default: on)                     |
| MOTOR_STATES_1        | State of the motor (default: off)                    |
| MOTOR_STATES_2        | State of the motor                                   |

## Exercises

### Exercise 0: Write your first stream

Goals:

* Build your first Kafka Stream application
* Learn about different Kafka Steams Operations
* Understand Stateless Operations in Kafka Streams

Exercise:

* Use the template [Exercise0Stream.java](uc-iot/kafka-stream/src/main/java/com/zuehlke/training/kafka/iot/stream/Exercise0Stream.java) to implement a stream
* Run the application and check the log output
* Play around with the different (stateless) operations

Hints:

* Use the `peek` Operation at any point in the stream to write a log output and observe the key and value of a message

Links:

* Kafka Streams Stateless Operations: https://kafka.apache.org/32/documentation/streams/developer-guide/dsl-api.html#stateless-transformations

### Exercise 1: Calculate the average value for a sensor over a 1min time frame

Goals:

* Learn about different Kafka Steams Operations
* Understand Stateful Operations in Kafka Streams
* Unterstand Windowing in Kafka Streams

Exercise:

* Use the template [Exercise1Stream.java](uc-iot/kafka-stream/src/main/java/com/zuehlke/training/kafka/iot/stream/Exercise1Stream.java) to implement a stream
* Filter for Measurements with numeric values only
* Group messages for the same sensor (= key)
* Perform a windowed aggreagation with a timeframe of 1min
* Use reduce to build the average of two messages
* Write the result to a new Kafka Topic

Hints:

* Have a look at the [Avro Schema](uc-iot/kafka-stream/src/main/resources/avro/values.avsc)
* Have a look at the Operations: `filter`, `groupByKey`, `windowedBy` and `reduce`

Links:

* Kafka Streams Windowing: https://kafka.apache.org/32/documentation/streams/developer-guide/dsl-api.html#windowing
* Kafka Streams Reduce (windowed): https://kafka.apache.org/32/documentation/streams/developer-guide/dsl-api.html#aggregating (Scroll down in Table)
