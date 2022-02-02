from pyspark import SparkContext, SparkConf
from pyspark.sql import SparkSession
from pyspark.sql.types import StructField, StructType, IntegerType, StringType

sc = SparkContext.getOrCreate(SparkConf().setMaster('spark://localhost:7077')
            .set("spark.hadoop.yarn.resourcemanager.hostname","172.22.0.2")
            .set("spark.hadoop.yarn.resourcemanager.address", "172.22.0.2:8032"))
sc.setLogLevel("INFO")


#spark = SparkSession.builder.master("spark://0db45e28966c:7077").getOrCreate()

csvDF=sc.textFile("hdfs://172.22.0.2:9000/user/root/data/players-madrid.csv")
#csvDF=spark.read.csv('hdfs://172.22.0.2:9000/user/root/data/players-madrid.csv')
print(csvDF.collect())
# sdf=spark.createDataFrame(rddFromTxt)
# sdf.show()
#sdf.show()
csvDF.show(5)