# Schema Registry

Connect to the schema registry

```
docker-compose exec schema-registry bash 
```

## Produce messages with a Avro Schema

Start a producer with the schema [sensor-1.avcs](uc-iot/schemas/avro/sensor-v1.avsc)

```
kafka-avro-console-producer \
  --topic avro-sensor \
  --broker-list broker:29092 \
  --property schema.registry.url=http://localhost:8081 \
  --property value.schema="$(< /schemas/avro/sensor-v1.avsc)"
```

Send some messages:

💡 You can ignore messages with `LEADER_NOT_AVAILABLE`

```
{"sensor_id":"mySensor","datetime":1234,"value":{"long": 999}}
{"sensor_id":"myMotor","datetime":1235,"value":{"string": "starting"}}
```

💡 Inspect the schema that has been registered to the topic `avro-sensor`: http://localhost:8080/ui/docker-kafka-server/schema

💡 You can also use schemas for keys

## Consume messages with the same Avro Schema

Start a consumer with the schema [sensor-1.avcs](uc-iot/schemas/avro/sensor-v1.avsc):

```
kafka-avro-console-consumer \
  --topic avro-sensor \
  --bootstrap-server broker:29092 \
  --property schema.registry.url=http://localhost:8081 \
  --property value.schema="$(< /schemas/avro/sensor-v1.avsc)" \
  --from-beginning
```

💡 If you are reading with the same schema, you can also remove the property `value.schema` from the command

## Produce messages with a newer Avro Schema (mandatory field)

Start a producer with the schema [sensor-2.avcs](uc-iot/schemas/avro/sensor-v2.avsc)

* This schema now includes a new mandatory field `type`

```
kafka-avro-console-producer \
  --topic avro-sensor \
  --broker-list broker:29092 \
  --property schema.registry.url=http://localhost:8081 \
  --property value.schema="$(< /schemas/avro/sensor-v2.avsc)"
```

Try to send a message that include the new mandatory field:

```
{"sensor_id":"mySensor","datetime":1234,"value":{"long": 999},"type":"m"}
```

📝 What happend? Check the log output and the AKHQ page

## Produce messages with a newer Avro Schema (optional field)

Start a producer with the schema [sensor-3.avcs](uc-iot/schemas/avro/sensor-v3.avsc)

* This schema adds a default value for `type` and makes it optional

```
kafka-avro-console-producer \
  --topic avro-sensor \
  --broker-list broker:29092 \
  --property schema.registry.url=http://localhost:8081 \
  --property value.schema="$(< /schemas/avro/sensor-v3.avsc)"
```

Try to send some messages with the new field:

```
{"sensor_id":"mySensor","datetime":1234,"value":{"long": 999},"type":"m"}
{"sensor_id":"myMotor","datetime":1235,"value":{"string": "starting"},"type":"state"}
```

💡 Inspect the schema that has been registered to the topic `avro-sensor`: http://localhost:8080/ui/docker-kafka-server/schema

📝 What is the version number of the schema?

## Consume messages with a older Avro Schema

Start a consumer with the schema [sensor-1.avcs](uc-iot/schemas/avro/sensor-v1.avsc):

```
kafka-avro-console-consumer \
  --topic avro-sensor \
  --bootstrap-server broker:29092 \
  --property schema.registry.url=http://localhost:8081 \
  --property value.schema="$(< /schemas/avro/sensor-v1.avsc)" \
  --from-beginning
```

📝 Are you able to read the data?

## Understand how schemas are used in a custom Kafka Client

Have a look at [SensorProducer.java](uc-iot/sensor/src/main/java/com/zuehlke/training/kafka/iot/sensor/SensorProducer.java)