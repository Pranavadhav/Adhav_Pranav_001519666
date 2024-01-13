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
    'my_spark_dag',
    default_args=default_args,
    description='DAG for Spark Streaming Job',
    schedule_interval='@daily',  # Set the desired schedule interval
)

spark_command = """
spark-submit --packages org.apache.hadoop:hadoop-aws:2.7.1 --conf spark.hadoop.fs.s3a.access.key=please enter your access key --conf spark.hadoop.fs.s3a.secret.key=please enter your secrete key --conf "spark.pyspark.python=python" test_stream.py
"""

run_spark_job = BashOperator(
    task_id='run_spark_job',
    bash_command=spark_command,
    dag=dag,
)

run_spark_job
