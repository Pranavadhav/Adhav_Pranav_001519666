from datetime import datetime, timedelta
from airflow import DAG
from airflow.operators.bash_operator import BashOperator

default_args = {
    'owner': 'airflow',
    'start_date': datetime(2023, 12, 12),
    'retries': 1,
    'retry_delay': timedelta(minutes=5),
}

dag = DAG(
    'start_real_time_stock_market_pipeline',
    default_args=default_args,
    description='Start DAG for Spark Streaming Job and Kafka Management',
    schedule_interval='0 9 * * *',  # Schedule to run daily at 9 AM
)

start_kafka = BashOperator(
    task_id='start_kafka',
    bash_command='systemctl start kafka',  
    dag=dag,
)

spark_command = """
spark-submit --packages org.apache.hadoop:hadoop-aws:2.7.1 --conf spark.hadoop.fs.s3a.access.key=please enter your access key --conf spark.hadoop.fs.s3a.secret.key=please enter your secrete key --conf "spark.pyspark.python=python" test_stream.py
"""

run_spark_job = BashOperator(
    task_id='run_spark_job',
    bash_command=spark_command,
    dag=dag,
)

start_kafka >> run_spark_job

