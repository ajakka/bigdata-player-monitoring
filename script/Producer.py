from kafka import KafkaProducer
from json import dumps

producer = KafkaProducer(
   value_serializer=lambda m: dumps(m).encode('utf-8'), 
   bootstrap_servers=['127.0.0.1:9093'])

producer.send("CLIENT",key={"key",1},value={"hello": "producer"})
producer.flush()

