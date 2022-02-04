from pyspark import SparkContext, SparkConf, SparkFiles
from pyspark.sql import SparkSession

sc = SparkContext.getOrCreate(SparkConf().setMaster('spark://soccer-spark:7077'))
# sc.setLogLevel("FATAL")

spark = SparkSession \
    .builder \
    .appName("Football players spark instance") \
    .getOrCreate()

# Load csv data
spk_df = spark.read.csv("/opt/bitnami/spark/players-madrid.csv",header=True, inferSchema= True)

# count players by club name
club_name_count = spk_df.groupBy("nationality").count()
club_name_count.show()

# print(df.head())

sc.stop()