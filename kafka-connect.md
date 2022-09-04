# Kafka Connect
TODO: Final wording.

## First source connector

Let's get started and create a file topic.
```
kafka-topics.sh --zookeeper localhost:2181 --create --topic file-topic --partitions 3 --replication-factor 1
# to describe a topic
kafka-topics.sh --zookeeper localhost:2181 --topic file-topic --describe
```

Access to connect container and create an empty file.

```
docker exec -it kafka-connect-01 bash  
# Create a file and give permission 
touch /tmp/input && chmod 777 /tmp/input
```

Let's register the connector.
```
curl -X POST http://localhost:8083/connectors \
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
* 💡 org.apache.kafka.connect.file.FileStreamSourceConnector is not by default in container and was copied.
* 💡 Converter are for the data format. TBD: Link

Let's investigate the connectors.
```
# Show all connectors
curl http://localhost:8083/connectors
# Check our specific connector
curl http://localhost:8083/connectors/file_source_connector
# Check the status of our connector
curl  'http://localhost:8083/connectors/file_source_connector/status'
```

Let's generate data and investigate our topic
```
# inside the container
echo "My new data." >> /tmp/input

# Outside the container. Alternativ you can use the akhq UI. 
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic file-topic --from-beginning
```

Create sink connector and consumer the data

```
# Inside the container
curl -X POST http://localhost:8083/connectors \
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
# Inside the container
tail -fn 10 /tmp/output
# Outside the container
kafka-console-producer.sh --bootstrap-server localhost:9092 --topic file-topic
```
💡 We recommend two consoles. 


Delete both connectors to clean up.
```
# Inside the container
curl -X DELETE http://localhost:8083/connectors/file_source_connector
curl -X DELETE http://localhost:8083/connectors/file_sink_connector

# The result should be empty now.
curl http://localhost:8083/connectors
```

