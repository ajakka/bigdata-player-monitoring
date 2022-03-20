from Spark import Spark
from pyspark.sql import SQLContext
class SparkReader:
    def __init__(self):
        pass

    @staticmethod
    def getPlayers():
        return Spark()\
            .getContext()\
            .textFile("hdfs://172.22.0.2:9000/user/root/data/players-madrid.csv")
    
    @staticmethod
    def getPlayersDF():
        return Spark()\
            .getSession("readData")\
            .read.csv("hdfs://172.22.0.2:9000/user/root/data/players-madrid.csv",header=True, inferSchema= True)
    @staticmethod
    def getIds():
        dataRdd=SparkReader.getPlayers()
        idsRdd=dataRdd.map(lambda x:x.split(',')[0])
        return idsRdd.collect()[1:]
    
    @staticmethod
    def exampleQuestion2():
        sc=Spark().getContext()
        data=SparkReader().getPlayersDF()
        nat_count = data.groupBy("nationality").count()
        nat_count.show()
SparkReader().exampleQuestion2()


