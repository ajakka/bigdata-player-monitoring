import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.JavaRDD;
import java.util.List;
import java.lang.System;

public class loadData {
    public static void main(String[] args) {
        SparkConf sc=new SparkConf().setMaster("spark://localhost:7077").setAppName("Test");
        JavaSparkContext context=new JavaSparkContext(sc);
        JavaRDD<String>data=context.textFile("hdfs://172.22.0.2:9000/user/root/data/players-madrid.csv");
        System.out.println(data.count());
    }
}
