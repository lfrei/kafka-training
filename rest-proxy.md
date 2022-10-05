# Rest-Proxy

[⬅️ Back to Kafka overview](README.md)

Make sure that the exercise environment is up and running:

```
docker-compose up -d
```

To be able to solve this exercises, the exercises from [KSQL](ksql.md) should be completed. You check that by looking at the `SENSOR_TOP_20` Topic:

http://localhost:8082/topics/SENSOR_TOP_20

If you need help, have a look at the API documentation:

https://docs.confluent.io/platform/current/kafka-rest/api.html

## Exercise 1: Create Topic

To create a new Topic, you first have to find out your cluster id. Navigate to:

http://localhost:8082/v3/clusters

Get the value from `cluster_id` and replace it in the following command to create a new Topic called `REST_PROXY_TEST`:

```
curl -X POST -H "Content-Type: application/json" \
 --data '{"topic_name":"REST_PROXY_TEST", "partitions_count": 5, "configs": []}' \
 "http://localhost:8082/v3/clusters/{cluster_id}/topics"
```

Check if the Topic as been created:

http://localhost:8082/topics/REST_PROXY_TEST

💡 If you want to produce messages you can follow the [tutorial](https://docs.confluent.io/platform/current/tutorials/examples/clients/docs/rest-proxy.html#produce-records) here.

## Exercise 2: Consume from Topic

Now you want to read data from the `SENSOR_TOP_20` Topic. Do so with the following commands.

Create a consumer:

```
curl -X POST  -H "Content-Type: application/vnd.kafka.v2+json" \
 --data '{"name": "top_20_consumer", "format": "avro", "auto.offset.reset": "earliest"}' \
 http://localhost:8082/consumers/top_20_consumer
```

💡 The data of this topics is formatted in Avro.

Subscribe to the Topic `SENSOR_TOP_20`:

```
curl -X POST -H "Content-Type: application/vnd.kafka.v2+json" --data '{"topics":["SENSOR_TOP_20"]}' \
http://localhost:8082/consumers/top_20_consumer/instances/top_20_consumer/subscription
```

Consume data:

```
curl -X GET -H "Accept: application/vnd.kafka.avro.v2+json" \
http://localhost:8082/consumers/top_20_consumer/instances/top_20_consumer/records
```

💡 If you want to have a formatted output, install [jq JSON processor](https://stedolan.github.io/jq/) and append `... | jq` to the command.

📝 Can you find your consumer using the Rest Proxy? Hint: Use this Url as entry point: http://localhost:8082/v3/clusters

After you are finished, remove the consumer again:

```
curl -X DELETE -H "Content-Type: application/vnd.kafka.v2+json" \
http://localhost:8082/consumers/top_20_consumer/instances/top_20_consumer
```
