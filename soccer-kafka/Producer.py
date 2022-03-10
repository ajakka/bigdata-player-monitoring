from kafka import KafkaProducer
from json import dumps
from data import get_data
from time import sleep
import threading

class Producer:
    def __init__(self,broker='127.0.0.1:9093'):
        self.producer=KafkaProducer(
        bootstrap_servers=[broker],value_serializer=self.json_serializer)
    
    def json_serializer(data):
        return dumps(data).encode("utf-8")

    def send(self,topic,data):
        self.producer.send(topic,data)
        self.producer.flush()
        sleep(2)
