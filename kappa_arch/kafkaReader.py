from Spark import Spark
import json
class KafkaReader:
    def __init__(self):
        pass

    @staticmethod
    def readFromKafka(topic="sensors"):
        return Spark().getSession("kafkaReader").read.format("kafka")\
        .option("kafka.bootstrap.servers", "localhost:9093") \
        .option("subscribe",topic) \
        .load()

    #returns array of dicts,each dict contains id of the player as str and the heartBeat
    def getData(self):
        heartBeats=[]
        df=KafkaReader().readFromKafka()
        df=df.withColumn('key_str', df['key'].cast('string').alias('key_str')).drop(
        'key').withColumn('value_str', df['value'].cast('string').alias('value_str')).drop('value')
        df=df.toPandas()
        df=df['value_str']
        for record in df:
            heartBeats.append(json.loads(record))
        return heartBeats
    
kr=KafkaReader()
print(kr.getData())
