import kafka.serializer.StringDecoder;
import kafka.serializer.StringEncoder;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.kafka010.ConsumerStrategy;
import org.apache.spark.sql.streaming.StreamingQueryException;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.kafka010.*;
import java.util.*;
import java.util.concurrent.TimeoutException;

public class kafkaReader {

    public static <MyLog> void main(String[] args) throws InterruptedException, StreamingQueryException, TimeoutException {
        SparkConf conf=new SparkConf().setMaster("local").setAppName("kafkaReader");
        JavaSparkContext jsc=new JavaSparkContext(conf);
        JavaStreamingContext jstream = new JavaStreamingContext(jsc, new Duration(20000));

        Map<String,Object> kafkaParams=new HashMap<>();
        kafkaParams.put("bootstrap.servers","localhost:9093");
        kafkaParams.put("key.deserializer", StringDeserializer.class);
        kafkaParams.put("value.deserializer", StringDeserializer.class);
        kafkaParams.put("group.id", "consumer");
        kafkaParams.put("auto.offset.reset", "earliest");
        kafkaParams.put("enable.auto.commit", false);
        Collection<String> topics = Collections.singleton("sensors");
        JavaInputDStream<ConsumerRecord<String, String>> directKafkaStream = KafkaUtils.<String,String>createDirectStream(jstream,
                LocationStrategies.PreferConsistent(), ConsumerStrategies.<String,String>Subscribe(topics,kafkaParams));
        directKafkaStream.foreachRDD(new VoidFunction<JavaRDD<ConsumerRecord<String, String>>>() {
            @Override
            public void call(JavaRDD<ConsumerRecord<String, String>> consumerRecordJavaRDD) throws Exception {
                consumerRecordJavaRDD.reduce(new Function2<ConsumerRecord<String, String>, ConsumerRecord<String, String>, ConsumerRecord<String, String>>() {
                    @Override
                    public ConsumerRecord<String, String> call(ConsumerRecord<String, String> v1, ConsumerRecord<String, String> v2) throws Exception {
                        String data=v1.value()+":"+v2.value();
                        System.out.println(data);
                        return null;
                    }
                });
            }
        });
        jstream.start();
        jstream.awaitTermination();
    }
}
