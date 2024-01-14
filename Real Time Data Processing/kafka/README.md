1. create topic 
2. start kafka server
3. Open new terminal and Produce data using = docker exec -it "give your kafka container ID" kafka-console-producer.sh --bootstrap-server localhost:9092 --topic test_topic
4. Open new terminal and consume produce data using = docker exec -it "give your kafka container ID" kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test_topic
