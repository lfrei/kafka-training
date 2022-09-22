curl -X POST  -H "Content-Type: application/vnd.kafka.v2+json" \
      --data '{"name": "my_consumer_instance", "format": "avro", "auto.offset.reset": "earliest"}' \
      http://localhost:8082/consumers/my_avro_consumer


      curl -X POST -H "Content-Type: application/vnd.kafka.v2+json" --data '{"topics":["myPlant"]}' \
      http://localhost:8082/consumers/my_avro_consumer/instances/my_consumer_instance/subscription



      curl -X GET -H "Accept: application/vnd.kafka.avro.v2+json" \
      http://localhost:8082/consumers/my_avro_consumer/instances/my_consumer_instance/records



      curl -X DELETE -H "Content-Type: application/vnd.kafka.v2+json" \
      http://localhost:8082/consumers/my_avro_consumer/instances/my_consumer_instance





      ///JSON

      curl -X POST  -H "Content-Type: application/vnd.kafka.v2+json" \
      --data '{"name": "my_consumer_instance", "format": "json", "auto.offset.reset": "earliest"}' \
      http://localhost:8082/consumers/my_json_consumer


  curl -X POST -H "Content-Type: application/vnd.kafka.v2+json" --data '{"topics":["metadataJson"]}' \
      http://localhost:8082/consumers/my_json_consumer/instances/my_consumer_instance/subscription


       curl -X GET -H "Accept: application/vnd.kafka.json.v2+json" \
      http://localhost:8082/consumers/my_json_consumer/instances/my_consumer_instance/records

        curl -X DELETE -H "Content-Type: application/vnd.kafka.v2+json" \
      http://localhost:8082/consumers/my_json_consumer/instances/my_consumer_instance


      ///ex

      curl -X POST  -H "Content-Type: application/vnd.kafka.v2+json" \
      --data '{"name": "top_20_consumer", "format": "json", "auto.offset.reset": "earliest"}' \
      http://localhost:8082/consumers/top_20_consumer


  curl -X POST -H "Content-Type: application/vnd.kafka.v2+json" --data '{"topics":["SENSOR_TOP_20"]}' \
      http://localhost:8082/consumers/top_20_consumer/instances/top_20_consumer/subscription


       curl -X GET -H "Accept: application/vnd.kafka.json.v2+json" \
      http://localhost:8082/consumers/top_20_consumer/instances/top_20_consumer/records

        curl -X DELETE -H "Content-Type: application/vnd.kafka.v2+json" \
      http://localhost:8082/consumers/top_20_consumer/instances/top_20_consumer

