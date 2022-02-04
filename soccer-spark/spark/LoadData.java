import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class LoadData {
//  public static void main(String[] args) {
//    SparkConf sc = new SparkConf().setMaster("spark://localhost:7077").setAppName("Test");
//    JavaSparkContext context = new JavaSparkContext(sc);
//    JavaRDD<String> data = context.textFile("hdfs://172.22.0.2:9000/user/root/data/players-madrid.csv");
//    System.out.println(data.count());
//  }

//  public static void main(String[] args) {
//    SparkConf sc = new SparkConf().setMaster("spark://localhost:7077").setAppName("Test");
//    SparkContext context = new SparkContext(sc);
//
//    SparkSession ss = SparkSession.builder().appName("My spark session").getOrCreate();
//
//    Dataset<Row> data = ss.read().csv("/opt/bitnami/spark/players-madrid.csv");
//
//    Dataset<Row> countData = data.groupBy("club_name").count();
//
//    countData.show();
//  }
}
