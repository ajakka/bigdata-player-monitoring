import kafka.serializer.DefaultDecoder;
import kafka.serializer.StringDecoder;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.SparkConf;
import org.apache.spark.TaskContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.*;
import org.apache.spark.streaming.kafka010.KafkaRDDPartition;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaPairReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.dstream.InputDStream;
import org.apache.spark.streaming.kafka010.*;
import scala.Array;
import scala.Tuple2;
import org.apache.spark.kafka010.KafkaTokenUtil$;

import java.util.*;
import java.util.concurrent.TimeoutException;

public class kafkaReader {

    public static <MyLog> void main(String[] args) throws InterruptedException, StreamingQueryException, TimeoutException {
        /*SparkConf conf=new SparkConf().setMaster("spark://127.0.1.1:7077").setAppName("kafka");
        // batchDuration - The time interval at which streaming data will be divided into batches
        JavaSparkContext sc=new JavaSparkContext(conf);
        JavaStreamingContext ssc = new JavaStreamingContext(sc, new Duration(20000));
        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", "127.0.0.1:9093");
        kafkaParams.put("key.deserializer", StringDeserializer.class);
        kafkaParams.put("value.deserializer", StringDeserializer.class);
        kafkaParams.put("group.id", "consumer");
        kafkaParams.put("auto.offset.reset", "earliest");
        kafkaParams.put("enable.auto.commit", false);
        Collection<String> topics = Arrays.asList("demo");
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
        ssc.start();
        ssc.awaitTermination();*/
        SparkSession spark = SparkSession.builder().appName("kafkaStream").master("spark://SVE1511Z1EB:7077")
                .getOrCreate();
        Dataset<Row> df = spark.readStream().format("kafka")
                .option("kafka.bootstrap.servers", "127.0.0.1:9093")
                .option("subscribe", "demo")
                .option("kafka.group.id","test")
                .option("includeHeaders", "true")
                .option("startingOffsets", "earliest")
                .load();
        Dataset<String> words = df.select("value").as(Encoders.STRING());
        //df=df.select(df.col("topic"),df.col("partition"));
        /*df = df.selectExpr("CAST(topic AS STRING)", "CAST(partition AS STRING)", "CAST(offset AS STRING)", "CAST(value AS STRING)");
        df = df.select(functions.col("topic"), functions.col("partition"), functions.col("offset"), functions.from_json(functions.col("value"), emp_schema).alias("data"));
        df = df.select("topic", "partition", "offset", "data.*");*/
        StreamingQuery q=words.writeStream().outputMode("append").format("console").start();
        //df.writeStream().format("console").start().awaitTermination();
        q.awaitTermination();
    }
}
