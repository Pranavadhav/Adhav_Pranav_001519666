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
    'stop_real_time_stock_market_pipeline',
    default_args=default_args,
    description='Stop DAG for Spark Streaming Job and Kafka Management',
    schedule_interval='0 17 * * *',  # Schedule to run daily at 5 PM
)

stop_spark_command = """
APP_ID=$(cat /tmp/spark_app_id.txt)
spark-submit --kill $APP_ID
"""

stop_spark_job = BashOperator(
    task_id='stop_spark_job',
    bash_command=stop_spark_command,
    dag=dag,
)

stop_kafka = BashOperator(
    task_id='stop_kafka',
    bash_command='docker exec -it <kafka_container_id> kafka-server-stop.sh', 
    dag=dag,
)

stop_spark_job >> stop_kafka
