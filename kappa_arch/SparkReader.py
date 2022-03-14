from Spark import Spark

class SparkReader:
    def __init__(self):
        pass

    @staticmethod
    def getPlayers():
        return Spark()\
            .getContext()\
            .textFile("hdfs://172.22.0.2:9000/user/root/data/players-madrid.csv")
    
    @staticmethod
    def getIds():
        dataRdd=SparkReader.getPlayers()
        idsRdd=dataRdd.map(lambda x:x.split(',')[0])
        return idsRdd.collect()[1:]

