### Exercise Kafka Security 

[⬅️ Back to Kafka overview](README.md)

Goals
* Create a CA and corresponding certificates for the broker and clients (consumer/producer).
* Enable TLS encryption.
* Enable client authentication.
* Create ACLs and grants access to a specific topics. Toy around with the configs.

💡 To Update your environment with new Docker Compose config use the following command in your training folder:
```
docker-compose up -d
```

Exercise
* Investigate and create the certificates with the script [create-certs.sh](/security/scripts/create-certs.sh)
* Copy the news certificates to "/security/certs".
* Alter and add the following attributes in your Docker Compose file.

```
# Create all certificates. 
cd security/certs
cp ../scripts/create-certs.sh  .
 ./create-certs.sh

# Remove the comments in Dock compose.
KAFKA_SSL_KEYSTORE_FILENAME: kafka.broker.keystore.jks 
KAFKA_SSL_KEYSTORE_CREDENTIALS: broker_keystore_creds
KAFKA_SSL_KEY_CREDENTIALS: broker_sslkey_creds
KAFKA_SSL_TRUSTSTORE_FILENAME: kafka.broker.truststore.jks
KAFKA_SSL_TRUSTSTORE_CREDENTIALS: broker_truststore_creds
```

Alter the advertised listener as well the security protocol. Afterwards update your docker environment. 

```
KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://broker:29092,PLAINTEXT_HOST://localhost:9092,SSL://broker:9093
KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT,SSL:SSL

# Check if all endpoints were published
docker logs broker | grep -i endpoint

# Excpected result:
[2022-10-05 17:54:39,794] INFO [SocketServer listenerType=ZK_BROKER, nodeId=1] Started data-plane acceptor and processor(s) for endpoint : ListenerName(PLAINTEXT) (kafka.network.SocketServer)
[2022-10-05 17:54:39,796] INFO [SocketServer listenerType=ZK_BROKER, nodeId=1] Started data-plane acceptor and processor(s) for endpoint : ListenerName(PLAINTEXT_HOST) (kafka.network.SocketServer)
[2022-10-05 17:54:39,797] INFO [SocketServer listenerType=ZK_BROKER, nodeId=1] Started data-plane acceptor and processor(s) for endpoint : ListenerName(SSL) (kafka.network.SocketServer)
```


Access the broker and produce a message. Investigate the blow given bash scripts als well as the properties file.
``` 
docker exec -it broker bash
cd /scripts$
# start console producer
./start-producer.sh
# start console consumer
./start-consumer.sh
```

Activate the client auth and active the keystore in the properties files in the folder at /scripts on the broker.  
```      
KAFKA_SSL_CLIENT_AUTH: 'required'
```

Create an ACL for the topic kafka-security-topic and try to produce/consume messages.
```

kafka-acls --authorizer-properties zookeeper.connect=zookeeper:2181 --add --allow-principal User:producer --operation all -topic kafka-security-topic

# Invistige the ACL
kafka-acls --authorizer-properties zookeeper.connect=zookeeper:2181 --list -topic kafka-security-topic

# Check the certficate within key or truststore.
keytool -list -v -keystore kafka.producer.keystore.jks
```

Links:
* https://kafka.apache.org/documentation/#security_authz
* https://docs.confluent.io/platform/current/kafka/authorization.html