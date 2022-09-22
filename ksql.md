Start your KSQL-client with the following command:

```
docker-compose exec ksqldb-cli ksql http://ksqldb-server:8088
```


you can check for topics, streams and tables with the following commands:

```
SHOW TOPICS;
SHOW TABLES;
SHOW STREAMS;
```

to check the content of your myPlant topic, use the following command

```
PRINT myPlant;
```

now you we create a ksql-strem based on our plant-topic

```
CREATE STREAM 
	myplant_avro(sensor_id varchar, datetime bigint, value varchar) 
WITH
	( KAFKA_TOPIC = 'myPlant', value_format='AVRO');
```

check your stream data:

```
SELECT * FROM myplant_avro  emit changes limit 5;
```

currently you see only new data that is generated on your topic. Thats ok for the time
being, however, if you want to see all your data you can configure your broker
as follows:

```
SET 'auto.offset.reset' = 'earliest';
```


see entries of the motor:

```
SELECT * FROM myplant_avro  where sensor_id='myMotor' emit changes limit 5;
```

now it's time to solve exercise 1 from the iot-labs with KSQL:

### Exercise 1: Write alerts for high measurement values to a new stream

You can try out to create your topic directly. However, we have the problem that 'value' with different datatypes is not compatible (currently) with avro:
* create a new topic and transform the content to json, using kafka connect (fastpath: create some date with this [command](uc-ksql/sensordata.json)) 
* create the stream, and write a query that only returns the top 20%
* (write the messages to the sensor_top_20 stream)



```
CREATE STREAM 
	myplant_json(sensor_id varchar, datetime bigint, value varchar) 
WITH
	( KAFKA_TOPIC = 'myPlantJson', value_format='JSON');
```

```
 select * from myplant_json where sensor_id='mySensor' emit changes;
```

```
select * from myplant_json where sensor_id='mySensor' and CAST(value as bigint)>800000 emit changes;
```

```
create stream sensor_top_20 as select * from myplant_json where sensor_id='mySensor' and CAST(value as bigint)>800000 emit changes;
```

### Exercise 2: Add metadata to messages

Now you have to join


```
CREATE STREAM 
	metadata_json (sensor_id varchar, unit varchar ) 
WITH
	( KAFKA_TOPIC = 'metadataJson', value_format='JSON');
```


```
select s.datetime, s.value, m.unit from myPlant_Json as s left join metadata_json as m within 7 days  ON s.sensor_id = m.sensor_id emit changes;
```