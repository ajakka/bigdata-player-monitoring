from pyspark.sql import SparkSession
import os
os.environ['PYSPARK_SUBMIT_ARGS'] = '--packages org.apache.spark:spark-streaming-kafka_2.11:1.6.2 pyspark-shell'
spark = SparkSession.builder\
    .appName("SparkKafka")\
    .master("spark://SVE1511Z1EB:7077").getOrCreate()

df=spark.readStream.format("kafka")\
    .option("kafka.bootstrap.servers", "localhost:9093") \
    .option("subscribe", "CLIENT") \
    .load()
df.show()
