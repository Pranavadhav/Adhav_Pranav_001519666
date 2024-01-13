from pyspark.sql import SparkSession
from pyspark.streaming import StreamingContext
from pyspark.streaming.kafka import KafkaUtils
from pyspark.sql.functions import from_json, col
from pyspark.sql.types import StructType, StructField, StringType

# Define the schema for the incoming JSON messages
json_schema = StructType([
    StructField("name", StringType(), True),
    StructField("Open", StringType(), True),
    StructField("High", StringType(), True),
    StructField("Low", StringType(), True),
    StructField("Close", StringType(), True),
    StructField("Adj Close", StringType(), True),
    StructField("Volume", StringType(), True),  # Assuming this is the Volume column
    StructField("CloseUSD", StringType(), True)
])

# Function to process each DataFrame from the Kafka stream
def process_data(df):
    # Parse the JSON column
    parsed_df = df.select(from_json(col("value"), json_schema).alias("json_data"))

    # Flatten the struct column
    flattened_df = parsed_df.select("json_data.*")

    # Remove the 'Volume' column
    flattened_df = flattened_df.drop('Volume')

    # Display the data types of each column
    print("Data Types:")
    flattened_df.dtypes

    # Perform additional processing as needed
    # ...

# Create a Spark session
spark = SparkSession.builder.appName("KafkaStreamingExample").getOrCreate()

# Create a StreamingContext with a batch interval of 1 second
ssc = StreamingContext(spark.sparkContext, 1)

# Set Kafka configurations
kafka_params = {
    "bootstrap.servers": "localhost:9092",  # Replace with the address of your Kafka server
    "key.deserializer": "org.apache.kafka.common.serialization.StringDeserializer",
    "value.deserializer": "org.apache.kafka.common.serialization.StringDeserializer",
    "group.id": "spark-streaming-group",
    "auto.offset.reset": "latest"
}

# Create a DStream that represents streaming data from Kafka
#kafka_stream = KafkaUtils.createDirectStream(ssc, ["test_topic"], kafka_params)

# Convert each RDD in the DStream to a DataFrame
kafka_stream.foreachRDD(lambda rdd: process_data(spark.read.json(rdd.map(lambda x: x[1]))))

# Start the streaming context
ssc.start()

# Wait for the streaming context to be stopped manually or by an external process
ssc.awaitTermination()
