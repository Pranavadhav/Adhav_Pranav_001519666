from airflow import DAG
from airflow.operators.python_operator import PythonOperator
from datetime import datetime
import mysql.connector
import psycopg2

# Default arguments for the DAG
default_args = {
    'owner': 'your_owner_name',
    'start_date': datetime(2024, 5, 24),
    'email': ['your_email@example.com'],
}

# Initialize the DAG
dag = DAG(
    'mysql_to_postgres_etl',
    default_args=default_args,
    description='An ETL pipeline to extract data from MySQL, transform, and load new data into PostgreSQL incrementally',
    schedule_interval='@daily',  # Adjust the schedule interval as needed
)

# Function to extract data from MySQL
def extract_from_mysql():
    # Connect to MySQL
    mysql_connection = mysql.connector.connect(
        user='root',
        password='your_mysql_password',
        host='127.0.0.1',
        database='sales'
    )
    mysql_cursor = mysql_connection.cursor()

    # Find out the last rowid from the PostgreSQL data warehouse
    postgres_connection = psycopg2.connect(
        user='postgres',
        password='your_postgres_password',
        host='127.0.0.1',
        database='postgres',
        port='5432'
    )
    postgres_cursor = postgres_connection.cursor()

    def get_last_rowid():
        SQL = "SELECT MAX(rowid) FROM sales_data"
        postgres_cursor.execute(SQL)
        last_row_id = postgres_cursor.fetchone()[0]
        return last_row_id

    last_row_id = get_last_rowid()
    print("Last row id on PostgreSQL data warehouse = ", last_row_id)

    # List out all records in MySQL database with rowid greater than the one on the PostgreSQL data warehouse
    def get_latest_records(maxrowid):
        SQL = "SELECT * FROM sales_data WHERE rowid > %s"
        mysql_cursor.execute(SQL, (maxrowid,))
        return mysql_cursor.fetchall()

    new_records = get_latest_records(last_row_id)

    print("New rows on staging data warehouse = ", len(new_records))

    # Disconnect from MySQL
    mysql_connection.close()

    # Disconnect from PostgreSQL
    postgres_connection.close()

    return new_records

# Function to transform data
def transform_data(new_records):
    transformed_records = []
    for record in new_records:
        # Perform transformation
        transformed_record = {
            'product_id': record[1],
            'customer_id': record[2],
            'quantity': record[3],
            'total_amount': record[3] * 100  # Example transformation: Multiply quantity by 100
        }
        transformed_records.append(transformed_record)
    return transformed_records

# Function to load data into PostgreSQL
def load_to_postgres(transformed_records):
    # Connect to PostgreSQL
    postgres_connection = psycopg2.connect(
        user='postgres',
        password='your_postgres_password',
        host='127.0.0.1',
        database='postgres',
        port='5432'
    )
    postgres_cursor = postgres_connection.cursor()

    # Insert the transformed records into PostgreSQL data warehouse
    SQL = "INSERT INTO sales_data (rowid, product_id, customer_id, quantity) VALUES (%s, %s, %s, %s)"  # Adjust columns accordingly
    for record in transformed_records:
        postgres_cursor.execute(SQL, record)
        print(record)
    postgres_connection.commit()

    # Disconnect from PostgreSQL
    postgres_connection.close()

# Create the extract_from_mysql task
extract_task = PythonOperator(
    task_id='extract_from_mysql',
    python_callable=extract_from_mysql,
    dag=dag,
)

# Create the transform_data task
transform_task = PythonOperator(
    task_id='transform_data',
    python_callable=transform_data,
    provide_context=True,
    dag=dag,
)

# Create the load_to_postgres task
load_task = PythonOperator(
    task_id='load_to_postgres',
    python_callable=load_to_postgres,
    provide_context=True,
    dag=dag,
)

# Set up task dependencies
extract_task >> transform_task >> load_task
