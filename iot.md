# IoT

## Configure a sensor

| Property           | Description                                           |
|--------------------|-------------------------------------------------------|
| SENSOR_SENSOR_ID   | ID of the sensor. Key of the kafka message            |
| SENSOR_PLANT_ID    | ID of the plant, where the sensor is from. Topic Name |
| SENSOR_INTERVAL_MS | Interval duration in ms (default: 10000)              |
| SENSOR_MIN_VALUE   | Minimal value the sensor can generate                 |
| SENSOR_MAX_VALUE   | Maximal value the sensor can generate                 |

## Configure a motor

| Property              | Description                                          |
|-----------------------|------------------------------------------------------|
| MOTOR_MOTOR_ID        | ID of the motor. Key of the kafka message            |
| MOTOR_PLANT_ID        | ID of the plant, where the motor is from. Topic Name |
| MOTOR_MAX_INTERVAL_MS | Max Interval duration in ms (default: 30000)         |
| MOTOR_STATES_0        | State of the motor (default: on)                     |
| MOTOR_STATES_1        | State of the motor (default: off)                    |
| MOTOR_STATES_2        | State of the motor                                   |
