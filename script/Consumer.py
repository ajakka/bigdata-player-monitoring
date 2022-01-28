from kafka import KafkaConsumer
from json import loads

consumer = KafkaConsumer(
   'CLIENT',
    auto_offset_reset='latest',
    enable_auto_commit=True,
    group_id='my-group-1',
    value_deserializer=lambda m: loads(m.decode('utf-8')),
    bootstrap_servers=['localhost:9093'])

for m in consumer:
    print(m.value)