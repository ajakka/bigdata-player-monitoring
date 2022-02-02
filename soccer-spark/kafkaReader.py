from pyspark.sql import SparkSession
import os
os.environ['PYSPARK_SUBMIT_ARGS'] = '--packages org.apache.spark:spark-streaming-kafka_2.10:1.6.0 pyspark-shell'
spark = SparkSession.builder\
    .appName("SparkKafka")\
    .master("spark://localhost:7077").getOrCreate()

df=spark.readStream.format("kafka")\
    .option("kafka.bootstrap.servers", "localhost:9093") \
    .option("subscribe", "CLIENT") \
    .load()
df.show()
