from kafka import KafkaProducer
from json import dumps
from data import get_data
from time import sleep
import threading
#value_serializer=lambda m: dumps(m).encode('utf-8') 
def json_serializer(data):
    return dumps(data).encode("utf-8")

producer = KafkaProducer(
   bootstrap_servers=['127.0.0.1:9093'],value_serializer=json_serializer)

if __name__ == "__main__":
    while 1 == 1:
        registered_user = get_data()
        producer.send("test", {"hi":123})
        producer.flush() 
        sleep(6)
