# Kafka Connect

[⬅️ Back to Kafka overview](README.md)

Make sure that the exercise environment is up and running:

```
docker-compose up -d
```

💡 **Important:** Every command needs to be executed from the Kafka Broker or Kafka Connect **Docker Container**. Open two consoles:

* ***Kafka Broker***: `docker exec -it broker bash`
* ***Kafka Connect***: `docker exec -it kafka-connect-01 bash`

## File source connector

Let's get started and create a file topic:

```
# From broker container
kafka-topics --bootstrap-server localhost:9092 --create --topic file-topic --partitions 3 --replication-factor 1
# Inspect the new topis
kafka-topics --bootstrap-server localhost:9092 --topic file-topic --describe
```

Access to Kafka Connect container and create an empty file:

```
# From connect container
# Create a file and give permission 
touch /tmp/input && chmod 777 /tmp/input
```

Let's register the file source connector:

```
# From broker container
curl -X POST http://kafka-connect-01:8083/connectors \
-H 'Accept: */*' \
-H 'Content-Type: application/json' \
-d '{
        "name": "file_source_connector",
        "config": {
        "connector.class": "org.apache.kafka.connect.file.FileStreamSourceConnector",
        "topic": "file-topic",
        "file": "/tmp/input",
        "value.converter": "org.apache.kafka.connect.storage.StringConverter"
    }
}'
```

* The connector names 'file_source_connector' is unique
* FileStreamSourceConnector is an implementation of a connector from the kafka connector API.
* `org.apache.kafka.connect.file.FileStreamSourceConnector` is not by default in the container and was copied see (docker-compose.yaml)
* Converter are for the data format. See [Converter configuration](https://www.confluent.io/blog/kafka-connect-deep-dive-converters-serialization-explained/#configuring-converters)

Let's investigate the connectors:

```
# From broker container
# Show all connectors
curl http://kafka-connect-01:8083/connectors
# Check our specific connector
curl http://kafka-connect-01:8083/connectors/file_source_connector
# Check the status of our connector
curl  'http://kafka-connect-01:8083/connectors/file_source_connector/status'
```

💡 By replacing kafka-connect-01 with localhost you can use your browser to perform the get requests: http://localhost:8083/connectors

Now let's generate data that the connector should ingest into kafka:

```
# From connect container
echo "My new data." >> /tmp/input
```

Start a consumer to check the file topic:

```
# From broker container
kafka-console-consumer --bootstrap-server broker:9092 --topic file-topic --from-beginning
```

💡 Generate some more messages to see that the connector is ingesting the data

## File Sink Connector

Create a file sink connector that writes to a output file:

```
# From broker container
curl -X POST http://kafka-connect-01:8083/connectors \
-H 'Accept: */*' \
-H 'Content-Type: application/json' \
-d '{
        "name": "file_sink_connector",
        "config": {
        "connector.class": "org.apache.kafka.connect.file.FileStreamSinkConnector",
        "topics": "file-topic",
        "file": "/tmp/output",
        "value.converter": "org.apache.kafka.connect.storage.StringConverter"
    }
}' 
```

💡 Investigate the connector with the commands we learned above

Check the output file that has been written by the connector:

```
# from connect container
tail -fn 10 /tmp/output
```

You can write more messages to the `/tmp/input` file or produce them to the topic:

```
# From broker container
kafka-console-producer --bootstrap-server localhost:9092 --topic file-topic
```

## Clean up

Delete both connectors to clean up:

```
# From connect container
curl -X DELETE http://kafka-connect-01:8083/connectors/file_source_connector
curl -X DELETE http://kafka-connect-01:8083/connectors/file_sink_connector
# The result should be empty now.
curl http://kafka-connect-01:8083/connectors
```
