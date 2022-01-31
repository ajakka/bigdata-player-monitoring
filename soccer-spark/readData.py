from pyspark import SparkContext, SparkConf
from pyspark.sql import SparkSession
from pyspark.sql.types import StructField, StructType, IntegerType, StringType
import pandas as pd

sc = SparkContext()

spark = SparkSession.builder.getOrCreate()
csvDF=spark.read.csv('hdfs://hadoop-master:9000/user/data/')
csvDF.show()