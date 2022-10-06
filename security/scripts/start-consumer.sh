#!/bin/bash
kafka-console-consumer --bootstrap-server broker:9093 --topic kafka-security-topic --from-beginning --consumer.config consumer.properties
