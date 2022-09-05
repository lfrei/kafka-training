# Witness Protection

## Scenario in short
* A double agent moves to canton Zurich and start to work for an underground organisation based in **Langstrasse**. His goal is to collect evidence to put them in jail. Afterwards he needs witness protection.
* On his journey the double agent is generating events like **moving**, **marriage**, etc. which are stored in a traditional database using the Swiss ech20 standard in XML.
* You work for the canton Zurich and your task is transfer all events to Kafka to establish .

## Initial Setup
* There is an MariaDB database prepared with test data. 
* The database contains simplified events based on Swiss **eCH-0020** standard in XML. 
* The folder **uc-witness-protection** contains the source for Kafka streams and material to support the exercises. 
* The Docker container **kafka-connect-01** is utilised in exercise.

## Exercises

### Exercise 0: Connect the database to Kafka

Goals:

* Use Kafka connect to store database rows as events in a Kafka topics
* Learn about setting up the first JDBC connector
* See a connector in action

Exercise:

* Open http://localhost:8085/ to explore the data in the database.
  Leave the field for the server blank and type for the **username** and **password** "**kafka-training**".
* Use the template [Exercise0jdbc-connector.json](/uc-witness-protection/connectors/Exercise0jdbc-connector.json) to create a Kafka connector sending a post request with the tool of your choice (curl, Postman,etc.).
* Discover the created events in Kafka. 
* Why is this configuration not the most suitable for our scenario?
* Bonus: The utilised JDBC connector "JdbcSourceConnector" is not a standard within the container **kafka-connect-01** . Can you figure out from where it is and how we added it?

Hints:

* Retrieve the events of your topic multiple times.

Links:

* https://www.confluent.io/blog/kafka-connect-deep-dive-jdbc-source-connector

💡 If you prefer the console to access the databases: mysql --protocol=TCP -u kafka-training -pkafka-training events


### Exercise 1: Alter the connector configuration

Goals:

* New events in the database will be only transferred once to Kafka.
* Learning to work with connectors and altering the configuration.

Exercise:

* Update your existing connector with a put request so that new database entries oare nly synchronised once. 
* Clean your existing topic so that there is no previous data inside. 
* Create a new database entry based on the event template [Exercise1demo-event.xml](/uc-witness-protection/connectors/Exercise1demo-event.xml) 

Hints:

* For creating a new event you can use phpMyAdmin and copy the template.

Links:

* https://www.confluent.io/blog/kafka-connect-deep-dive-jdbc-source-connector/#incremental-ingest

💡 If you prefer the console to access to the databases: mysql --protocol=TCP -u kafka-training -pkafka-training events


### Exercise 2: Use Kafka streams to forward the events to tax department
TODO: Tailored to Lukas streams. 

Goals:
* Include Kafka streams to the existing environment
* Create a topic for the tax department and forward every event which contains "<eCH-0020:moveIn>" to the new topic.

Exercise:

* Create a new topic for the tax department with the name **"tax-department"**
* Use the template [Exercise2Stream.java](uc-witness-protection/kafka-stream/src/main/java/com/zuehlke/training/kafka/witnessprotection/broker/stream/Exercise2Stream.java) to implement a stream
* Run the application and check the log output
* Generate events which fulfill the criteria to be in the topics **"tax-department"** and some who doesn't.

Hints:

* ...

Links:
* https://developer.confluent.io/tutorials/filter-a-stream-of-events/confluent.html


### Exercise 3: Consume the events with consumer groups

Goals:

* Share the data between two consumer.
* Learn how to utilise Kafka mechanism for horizontal scaling which is called consumer groups. 

Exercise:

* Choose a topic of you choice. For each consumer is one partition requirement. Make sure you have enough portions as topics.
* Distribute the events for two consumer. One consumer should not see event of another consumer. 
* Generate test events to simulate the distribution of events.

Hints:

* If you need to generate data in a convenient way the insert dataset function in phpMyadmin allows you copying sql and running it multiple times.

Links:
* https://docs.confluent.io/platform/current/clients/consumer.html

### Exercise 4: Client authentication and ACLs

docker exec -it broker bash
/etc/kafka/server.properties
https://blog.softwaremill.com/editing-files-in-a-docker-container-f36d76b9613c

Goals:

* Enable Client Authentication
* Create ACLs with grants access to specific topics. 

Exercise:

Hints:

* ...

Links:
* https://kafka.apache.org/documentation/#security_authz
* https://docs.confluent.io/platform/current/kafka/authorization.html


### Exercise 5: Compacting + Tombstone

For this scenario we should configure the following specific topic configs:

segment.ms: 86400000 (24 hours)
this makes sure that the log segment will roll and allow for the record to be compacted. Default is 7 days.

delete.retention.ms: 172800000 (48 hours)
this represents the time the tombstone will be kept on the topic. This should not be too short in order to the consumers be able to consume it and delete their internal records or any other appropriate action.

Goals:

* ...

Exercise:

* ...

Hints:

* ...

Links:
* https://medium.com/@damienthomlutz/deleting-records-in-kafka-aka-tombstones-651114655a16
* https://kafka.apache.org/documentation.html#compaction
* https://docs.confluent.io/platform/current/installation/configuration/topic-configs.html
