We suggest to execute these commands from the Docker broker container.

Alter the topic mysql-01-events to more than one partion.
```
kafka-topics --zookeeper zookeeper:2181 --alter --topic mysql-01-events --partitions 2
```

Create two consumer for consumer group t1 in two different terminals with the same following command:
```
kafka-console-consumer --bootstrap-server broker:9092 --topic mysql-01-events --group t1
```
