
Delete the topic mysql-01-events and create a new one.
```
kafka-topics --zookeeper zookeeper:2181 --create --topic mysql-01-events --partitions 2 --replication-factor 1
```

Create two consumer for consumer group t1 in two different terminals with the same following command:
```
kafka-console-consumer --bootstrap-server broker:9092 --topic mysql-01-events --group t1
```