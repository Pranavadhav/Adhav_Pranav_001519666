#!/bin/sh

## Write your code here to load the data from sales_data table in Mysql server to a sales.csv.
## Select the data which is not more than 4 hours old from the current time.

mysql --host=127.0.0.1 --port=3306 --user=root --password=<'password'> -e "use sales; SELECT * FROM sales_data where timestamp >= NOW() - INTERVAL 24 HOUR INTO OUTFILE '/home/project/sales.csv' FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n';"

export PGPASSWORD=<'password'>;




psql --username=postgres --host=localhost --dbname=sales_new -c "\COPY sales_data(rowid,product_id,customer_id,price,quantity,timestamp) FROM '/home/project/sales.csv' delimiter ',' csv header;" 

 ## Delete sales.csv present in location /home/project
rm /home/project/sales.csv
 ## Write your code here to load the DimDate table from the data present in sales_data table

psql --username=postgres --host=localhost --dbname=sales_new -c  "INSERT INTO DimDate (dateid, day, month, year)
SELECT 
    (EXTRACT(YEAR FROM timestamp)::TEXT || EXTRACT(MONTH FROM timestamp)::TEXT || EXTRACT(DAY FROM timestamp)::TEXT)::INT AS dateid,
    EXTRACT(DAY FROM timestamp) AS day,
    EXTRACT(MONTH FROM timestamp) AS month,
    EXTRACT(YEAR FROM timestamp) AS year
FROM sales_data;"

## Write your code here to load the FactSales table from the data present in sales_data table

psql --username=postgres --host=localhost --dbname=sales_new -c  "INSERT INTO FactSales (rowid, product_id, custome_id, price, total_price)
SELECT 
    rowid,
    product_id,
    customer_id as custome_id,
    price,
    quantity * price AS total_price
FROM sales_data;"

 ## Write your code here to export DimDate table to a csv

psql --username=postgres --host=localhost --dbname=sales_new -c "\COPY (SELECT * FROM DimDate) TO '/home/project/dimdate.csv' WITH CSV HEADER;"

 ## Write your code here to export FactSales table to a csv
 
psql --username=postgres --host=localhost --dbname=sales_new -c "\COPY (SELECT * FROM FactSales) TO '/home/project/factsales.csv' WITH CSV HEADER;"

