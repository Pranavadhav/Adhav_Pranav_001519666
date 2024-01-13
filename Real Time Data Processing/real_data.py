from pyspark.sql import SparkSession
from pyspark.sql.types import StructType, StructField, StringType, IntegerType, TimestampType, LongType
from pyspark.sql.functions import from_json

# Create a SparkSession
aws_id = "your aws key"
aws_key = "your aws secret key"

# spark = SparkSession.builder.appName("SparkTest").getOrCreate()

# Define the schema for the incoming JSON messages
json_schema = StructType([
    StructField("Index", StringType(), True),
    StructField("Open", StringType(), True),
    StructField("High", StringType(), True),
    StructField("Low", StringType(), True),
    StructField("Close", StringType(), True),
    StructField("Adj Close", StringType(), True),
    StructField("Volume", StringType(), True),  # Assuming this is the Volume column
    StructField("CloseUSD", StringType(), True)
])

spark = SparkSession.builder.appName("write_real_data_topic")\
        .getOrCreate()
spark._jsc.hadoopConfiguration().set("fs.s3a.awsAccessKeyId", aws_id)
spark._jsc.hadoopConfiguration().set("fs.s3a.awsSecretAccessKey", aws_key)
spark.sparkContext.setLogLevel("WARN") # Reduce logging verbosity

parsed_json_col = "parsed_json"

df = spark.readStream.format("kafka")\
    .option("kafka.bootstrap.servers", "kafka:9092") \
    .option("subscribe", "test_topic") \
    .option("startingOffsets", "earliest") \
    .load()

df_parsed = df.selectExpr("CAST(value AS STRING) AS value") \
    .select(from_json("value", json_schema).alias(parsed_json_col)) \
    .selectExpr(f"{parsed_json_col}.*")

s3_bucket = "s3a://realstorage"
s3_path = "temp"
checkpoint_location = "s3a://realstorage/checkpoints/"
# Write DataFrame to AWS S3
df_parsed.writeStream \
    .format("csv") \
    .option("header", "true") \
    .outputMode("append") \
    .option("path", f"{s3_bucket}/{s3_path}") \
    .option("checkpointLocation", checkpoint_location) \
    .start() \
    .awaitTermination()

# Stop the SparkSession
spark.stop()
