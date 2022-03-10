from pyspark import SparkContext, SparkConf
from pyspark.sql import SparkSession
from pyspark.sql.types import StructField, StructType, IntegerType, StringType

class Spark:
    def __init__(self):
        pass

    def getContext(self,master="local"):
        sc = SparkContext.getOrCreate(SparkConf().setMaster(master)
            .set("spark.hadoop.yarn.resourcemanager.hostname","172.22.0.2")
            .set("spark.hadoop.yarn.resourcemanager.address", "172.22.0.2:8032"))
        sc.setLogLevel("INFO")
        return sc
        
    def getSession(self,name,master="local"):
        spark = SparkSession.builder\
        .appName(name)\
        .master(master).getOrCreate()
        return spark
    
        