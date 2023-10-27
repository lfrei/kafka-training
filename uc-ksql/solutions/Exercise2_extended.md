if you run the query from the beginning, you observe that the metadata table is correctly mapped in time. With other words, KSQL knows that for the old entries you had no unit (therefore value null), then you had cm, and the most recent records will be mapped in m. This is correct for event-sourcing, since you can reproduce the state of your application at any point in time.

However, if you wish the system to behave differently, you have to inject a record that 'goes back in time' you can do this as follwos in KSQL:

 * drop your `metadata_table`

💡 When creating a table, you have different properties do influence the behaviour of this table. Have a look to https://docs.ksqldb.io/en/0.8.1-ksqldb/developer-guide/ksqldb-reference/create-table/

We will use now the mapping of the timestamp from your payload to the KSQL-Table:

```
drop table metadata_table;

CREATE TABLE metadata_table(sensor_id VARCHAR PRIMARY KEY, type VARCHAR, timest	 BIGINT) 
WITH (KAFKA_TOPIC = 'metadataJson',  TIMESTAMP='timest', value_format='JSON');
```

Now you have to inject a record on the metadata table with a timestamp that is before the first record. You can just insert your metadata 'inserted' on unix datetime 0:

```
{"sensor_id": "mySensor", "type": "cm", "timest": "0"}
{"sensor_id": "myMotor", "type": "state", "timest": "0"}
```

if you do now your query again, you will see the expected result: 


```
SELECT s.sensor_id, s.datetime, s.value, m.type FROM myplant_sensors_stream AS s LEFT JOIN metadata_table AS m ON s.sensor_id = m.sensor_id EMIT CHANGES;
```



