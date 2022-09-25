# Kafka Connect

[⬅️ Back to Kafka overview](README.md)

## First source connector

💡 **Important:** Every command needs to be executed from the Kafka Broker or Kafka Connect **Docker Container**. We recommend two consoles. 

* Broker: docker exec -it  broker bash
* Kafka Connect: docker exec -it  kafka-connect-01 bash


Let's get started and create a file topic. 
```
# From broker container
kafka-topics --zookeeper zookeeper:2181 --create --topic file-topic --partitions 3 --replication-factor 1
# to describe a topic
kafka-topics --zookeeper zookeeper:2181 --topic file-topic --describe
```

Access to connect container and create an empty file.

```
# From connect container
# Create a file and give permission 
touch /tmp/input && chmod 777 /tmp/input
```

Let's register the connector.
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
* 💡 The connector names 'file_source_connector' is unique
* 💡 FileStreamSourceConnector is an implementation of a connector from the kafka connector API.
* 💡 **org.apache.kafka.connect.file.FileStreamSourceConnector** is not by default in container and was copied.
* 💡 Converter are for the data format. See https://www.confluent.io/blog/kafka-connect-deep-dive-converters-serialization-explained/#configuring-converters

Let's investigate the connectors.
```
# Show all connectors
curl http://kafka-connect-01:8083/connectors
# Check our specific connector
curl http://kafka-connect-01:8083/connectors/file_source_connector
# Check the status of our connector
curl  'http://kafka-connect-01:8083/connectors/file_source_connector/status'
```
* 💡 By replacing kafka-connect-01 with localhost you can use your browser to perform the get requests.

Let's generate data and investigate our topic
```
# From connect container
echo "My new data." >> /tmp/input

# # From broker container
kafka-console-consumer --bootstrap-server broker:9092 --topic file-topic --from-beginning
```

Create sink connector and consumer the data

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

Check the output and produce content
```
# from connect  container
tail -fn 10 /tmp/output
# From broker container
kafka-console-producer --bootstrap-server localhost:9092 --topic file-topic
```



Delete both connectors to clean up.
```
# From connect container
curl -X DELETE http://kafka-connect-01:8083/connectors/file_source_connector
curl -X DELETE http://kafka-connect-01:8083/connectors/file_sink_connector

# The result should be empty now.
curl http://kafka-connect-01:8083/connectors
```

