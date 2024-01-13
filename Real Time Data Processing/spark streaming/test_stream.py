from pyspark.sql import SparkSession

# Create a SparkSession
aws_id = "AKIASBZ6GWNEWENWAV5G"
aws_key = "AKef0E9vwALGKjVGjWsMASS7OpUvlq5zLGBl73/P"

spark = SparkSession.builder.appName("SparkTest").getOrCreate()
spark._jsc.hadoopConfiguration().set("fs.s3a.awsAccessKeyId", aws_id)
spark._jsc.hadoopConfiguration().set("fs.s3a.awsSecretAccessKey", aws_key)

# Create a DataFrame with some sample data
data = [("Alice", 1), ("Bob", 2), ("Catherine", 3)]
columns = ["Name", "Value"]
df = spark.createDataFrame(data, columns)

# Show the DataFrame
df.show()

# Define AWS S3 credentials and destination path

s3_bucket = "s3a://realstorage"
s3_path = "temp"

# Write DataFrame to AWS S3
df.write \
    .format("csv") \
    .option("header", "true") \
    .mode("overwrite") \
    .option("path", f"{s3_bucket}/{s3_path}") \
    .save()

# Stop the SparkSession
spark.stop()