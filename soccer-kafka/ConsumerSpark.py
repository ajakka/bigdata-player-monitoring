from pyspark.sql import SparkSession
from pyspark import SparkContext, SparkConf

appName = "Kafka reader"
sc = SparkContext.getOrCreate(SparkConf().setMaster('local'))
sc.setLogLevel("INFO")
spark = SparkSession.builder.getOrCreate()

kafka_servers = "localhost:9093"

df = spark \
    .read \
    .format("kafka") \
    .option("kafka.bootstrap.servers", kafka_servers) \
    .option("subscribe", "test") \
    .load()
df = df.withColumn('key_str', df['key'].cast('string').alias('key_str')).drop(
    'key').withColumn('value_str', df['value'].cast('string').alias('key_str')).drop('value')
df.show(5)