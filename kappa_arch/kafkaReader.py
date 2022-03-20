from Spark import Spark
import json
import pandas as pd
from datetime import datetime
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
    def getData(self,folderPath):
        heartBeats=[]
        df=KafkaReader().readFromKafka()
        df=df.withColumn('key_str', df['key'].cast('string').alias('key_str')).drop(
        'key').withColumn('value_str', df['value'].cast('string').alias('value_str')).drop('value')
        df=df.toPandas()
        df=df['value_str']
        for record in df:
            row=json.loads(record)
            heartBeats.append([list(row.keys())[0],list(row.values())[0]])
        df=pd.DataFrame(heartBeats,columns=['id','hb'])
        df=df.groupby('id').agg([pd.np.mean])
        df.to_csv(path_or_buf=f"{folderPath}/{datetime.today().strftime('%Y-%m-%d')}.csv", sep=',')
    
kr=KafkaReader()
kr.getData("/home/mustapha/bigdata-player-monitoring/kappa_arch/data")