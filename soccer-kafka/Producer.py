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

    def send(self,topic="sensors",data)
if __name__ == "__main__":
    while 1 == 1:
        registered_user = get_data()
        producer.send("test", {"hi":123})
        producer.flush()
        sleep(6)
