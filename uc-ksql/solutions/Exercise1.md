# Exercise 1

Stream for high sensor measurements:

```
CREATE stream sensor_top_20 AS SELECT * FROM myplant_sensors_stream WHERE value > 800000 EMIT CHANGES;
SELECT * FROM sensor_top_20 EMIT CHANGES;
```

Stream for motor error states:

```
CREATE stream motor_errors AS SELECT * FROM myplant_motors_stream WHERE value = 'error' EMIT CHANGES;
SELECT * FROM motor_errors EMIT CHANGES;
```
