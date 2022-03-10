from Spark import Spark

class SparkReader:
    def __init__(self):
        pass

    @staticmethod
    def getPlayers(self):
        return Spark()\
            .getContext()\
            .textFile("hdfs://172.22.0.2:9000/user/root/data/players-madrid.csv")

