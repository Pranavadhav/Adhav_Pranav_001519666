import pandas as pd 
dataframe = pd.read_csv('realstock.csv')
from confluent_kafka import Producer
import json

# Define Kafka broker and topic
bootstrap_servers = 'host.docker.internal:9092'
topic = 'test_topic'

# Configure the Kafka producer
producer_config = {
    'bootstrap.servers': bootstrap_servers
}

producer = Producer(producer_config)

# Produce your name as a message to the Kafka topic
#message = {'name': 'Adhav'}
while True:
    message_dict = dataframe.sample(1).to_dict(orient= "records")[0]
    message_str = json.dumps(message_dict)
    producer.produce(topic, value=message_str)

# Wait for any outstanding messages to be delivered and delivery reports received
producer.flush()