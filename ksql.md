# KSQL

[⬅️ Back to Kafka overview](README.md)

Make sure that the exercise environment is up and running:

```
docker-compose up -d
```

Start the KSQL CLI:

```
docker-compose exec ksqldb-cli ksql http://ksqldb-server:8088
```

Explore Topics, Streams and Tables:

```
SHOW TOPICS;
SHOW TABLES;
SHOW STREAMS;
```

💡 You can find all possible statements in the [KSQL Reference](https://docs.ksqldb.io/en/latest/developer-guide/ksqldb-reference/)

## Create a Stream to work with the myPlant Topic

Check the content of the myPlant Topic:

```
PRINT myPlant;
```

Create a Stream for the myPlant Topic:

```
CREATE STREAM myplant_stream(sensor_id varchar, datetime bigint, value STRUCT<STRING VARCHAR, LONG BIGINT>) 
WITH (KAFKA_TOPIC = 'myPlant', value_format='AVRO');
```

Show all Streams again to see that `myplant_stream` has been created:

```
SHOW STREAMS;
```

Show how the Stream `myplant_stream` is defined:

```
DESCRIBE myplant_stream;
```

Check the content of your Stream:

```
SELECT * FROM myplant_stream emit changes;
```

Currently you see only new data that is generated on your Topic. If you want to see all data, you can change the offset reset:

```
SET 'auto.offset.reset' = 'earliest';
```

💡 See the possible [data types](https://docs.ksqldb.io/en/latest/reference/sql/data-types/) in the KSQL documentation.

## Create a Stream from another Stream for Sensors and Motors

Now create separate Streams for sensors and motors using the existing `myplant_stream` Stream:

```
CREATE STREAM myplant_sensors_stream
AS select sensor_id, datetime, value->long as value from myplant_stream where sensor_id = 'mySensor';
```

```
CREATE STREAM myplant_motors_stream
AS select sensor_id, datetime, value->string as value from myplant_stream where sensor_id = 'myMotor';
```

Check the Streams again to see that they have been created:

```
SHOW STREAMS;
```

Check the content of your new Streams:

```
SELECT * FROM myplant_sensors_stream emit changes limit 5;
SELECT * FROM myplant_motors_stream emit changes limit 5;
```

💡 Have a look at the topics that have been created in [AKHQ](http://localhost:8080/ui/docker-kafka-server/topic)

### Exercise 1: Write alerts for high measurement values to a new stream

Exercise:

* Write a new stream called `sensor_top_20` that only outputs the the highest 20% of sensor measurements

Hints:

* You already have a stream that contains sensor values only
* Check the configured max values of the sensor. The default is 1 Mio

Strech Goal:

* Write a new stream called `motor_errors` that only outputs motors in state error

### Exercise 2: Add metadata to messages

Preparation:

* Navigate to [AKHQ](http://localhost:8080/ui/docker-kafka-server/topic) to create the `metadataJson` topic
  * Create a new topic
    * Select Button 'Create a topic'
    * Enter Name `metadataJson`
    * Click 'Create'
  * Add metadata to topic
    * Select topic `metadataJson` and click on magnifier icon
    * Select Button 'Produce to Topic'
    * Enter Key `mySensor` and value `{"sensor_id": "mySensor", "type": "cm"}`, Select 'Produce' Button
    * Enter Key `myMotor` and value `{"sensor_id": "myMotor", "type": "state"}`, Select 'Produce' Button

* Create a Table:

```
CREATE TABLE metadata_table(sensor_id VARCHAR PRIMARY KEY, type VARCHAR) 
WITH (KAFKA_TOPIC = 'metadataJson', value_format='JSON');
```
  
Exercise:

* Write a select statement that
  * joins the `myPlant_sensors_stream` stream with the `metadata_table` table
  * outputs all sensor fields with the `type`

📝 What happens if you add a new metadata value for the sensor in the topic?
