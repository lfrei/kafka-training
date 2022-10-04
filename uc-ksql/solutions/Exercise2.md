# Exercise 2

Select statement:

```
SELECT s.sensor_id, s.datetime, s.value, m.type FROM myplant_sensors_stream AS s LEFT JOIN metadata_table AS m ON s.sensor_id = m.sensor_id EMIT CHANGES;
```