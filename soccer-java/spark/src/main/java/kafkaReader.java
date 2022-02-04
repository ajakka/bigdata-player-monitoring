import kafka.serializer.StringDecoder;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.SparkConf;
import org.apache.spark.TaskContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.kafka010.*;
import scala.Tuple2;

import java.util.*;
public class kafkaReader {
    public static void main(String[] args) {
        SparkConf sc=new SparkConf().setMaster("spark://SVE1511Z1EB:7077").setAppName("kafka");
        // batchDuration - The time interval at which streaming data will be divided into batches
        JavaStreamingContext ssc = new JavaStreamingContext(sc, new Duration(20000));
        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", "localhost:9093");
        kafkaParams.put("key.deserializer", StringDeserializer.class);
        kafkaParams.put("value.deserializer", StringDeserializer.class);
        kafkaParams.put("group.id", "my-group-1");
        kafkaParams.put("auto.offset.reset", "earliest");
        kafkaParams.put("enable.auto.commit", false);
        Collection<String> topics = Arrays.asList("CLIENT");
        JavaInputDStream<ConsumerRecord<String, String>> stream =
                KafkaUtils.createDirectStream(ssc, LocationStrategies.PreferConsistent(),
                        ConsumerStrategies.<String,String>Subscribe(topics,kafkaParams));
        stream.mapToPair(record -> new Tuple2<>(record.key(), record.value()));

        stream.foreachRDD(rdd -> {
            OffsetRange[] offsetRanges = ((HasOffsetRanges) rdd.rdd()).offsetRanges();
            rdd.foreachPartition(consumerRecords -> {
                OffsetRange o = offsetRanges[TaskContext.get().partitionId()];
                System.out.println(
                        o.topic() + " " + o.partition() + " " + o.fromOffset() + " " + o.untilOffset());
            });
        });
    }
}
