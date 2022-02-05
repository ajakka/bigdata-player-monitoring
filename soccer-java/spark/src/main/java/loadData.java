import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.JavaRDD;
import java.lang.System;

public class loadData {
    public static void main(String[] args) {
        SparkConf sc=new SparkConf().setMaster("spark://SVE1511Z1EB:7077").setAppName("Test");
        JavaSparkContext context=new JavaSparkContext(sc);
        JavaRDD<String>data=context.textFile("hdfs://172.22.0.2:9000/user/root/data/players-madrid.csv");
        JavaRDD<String> rdd2=data;
        System.out.println(rdd2.count());
    }
//  public static void main(String[] args) {
//    SparkConf sc = new SparkConf().setMaster("spark://localhost:7077").setAppName("Test");
//    SparkContext context = new SparkContext(sc);
//
//    SparkSession ss = SparkSession.builder().appName("My spark session").getOrCreate();
//
//    Dataset<Row> data = ss.read().csv("./data/players-madrid.csv");
//
//    Dataset<Row> countData = data.groupBy("club_name").count();
//
//    countData.show();
//  }
}
