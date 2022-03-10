class SparkConsumer:
    def __init__(self):
        pass
    
    @staticmethod
    def consumer(broker="localhost:9093",topic="sensors"):
        return spark \
                .read \
                .format("kafka") \
                .option("kafka.bootstrap.servers", broker) \
                .option("subscribe", topic) \
                .load()

""" df = df.withColumn('key_str', df['key'].cast('string').alias('key_str')).drop(
    'key').withColumn('value_str', df['value'].cast('string').alias('key_str')).drop('value')
df.show(5) """