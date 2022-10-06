#!/bin/bash
kafka-console-producer --broker-list broker:9093 --topic kafka-security-topic  --producer.config producer.properties
