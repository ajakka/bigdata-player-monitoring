from kafka import KafkaConsumer
import json

if __name__ == "__main__":
    consumer = KafkaConsumer(
        "CLIENT",
        bootstrap_servers='localhost:9093',
        auto_offset_reset='earliest',
        group_id="my-group-1")
    print("starting the consumer")
    for msg in consumer:
        print("Registered User = {}".format(json.loads(msg.value)))