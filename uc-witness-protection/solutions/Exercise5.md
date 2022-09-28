## Exercise 5
We need to configure two attributes: **cleanup.policy** and **max.compaction.lag.ms**
* Attribute: **cleanup.policy**
* Definition: A string that is either "delete" or "compact" or both. This string designates the retention policy to use on old log segments. The default policy ("delete") will discard old segments when their retention time or size limit has been reached. The "compact" setting will enable log compaction on the topic.
* Answer:  Log compaction is a mechanism to give finer-grained per-record retention, rather than the coarser-grained time-based retention.  That is why need to set the value to **compact**. .

* **max.compaction.lag.ms:** The maximum time a message will remain ineligible for compaction in the log. Only applicable for logs that are being compacted.
* Answer: For this attribute we should define a reasonable value for the demo case like 3 minutes (180000 ms).

Let us create some data for the message key "agent" with phpMyAdmin.
```
INSERT INTO `events` (`ID`, `XML_EVENT`, `CREATETIME`, `PERSON_IDENTIFIER`) VALUES (NULL, 'payload', current_timestamp(), 'agent');
INSERT INTO `events` (`ID`, `XML_EVENT`, `CREATETIME`, `PERSON_IDENTIFIER`) VALUES (NULL, 'payload', current_timestamp(), 'agent');
INSERT INTO `events` (`ID`, `XML_EVENT`, `CREATETIME`, `PERSON_IDENTIFIER`) VALUES (NULL, 'payload', current_timestamp(), 'agent');
```
Now wait until the defined 3 minutes are over and crate a new message for the key **agent**. Kafkacat is a very convenient way to do that. 

```
# Install on Ubutntu 
sudo apt install kafkacat

# Mac
brew install kcat

# Sending an empty message for the topic mysql-01-events.
echo "agent:dummydata" | kafkacat -b localhost -t mysql-01-events  -Z -K:

# You can monitor your broker in the meanwhile and see how a new segment is created. 
watch ls -lisah /var/lib/kafka/data/mysql-01-events-0
```

All messages should now be compacted. 