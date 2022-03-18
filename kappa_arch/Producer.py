from kafka import KafkaProducer
from json import dumps
from time import sleep
from random import randrange
import threading
import SparkReader

class Producer:
    def __init__(self,broker='127.0.0.1:9093'):
        self.producer=KafkaProducer(
        bootstrap_servers=[broker],value_serializer=lambda x: dumps(x).encode('utf-8'))

    def send(self,topic="sensors"):
        for id in SparkReader.SparkReader().getIds():
            self.producer.send(topic,value={int(id):randrange(150, 180)})
            self.producer.flush()
            #sleep(2)

Producer().send()


