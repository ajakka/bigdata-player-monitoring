import org.apache.spark.SparkContext;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
public class loadData {
    public static void main(String[] args) {
        SparkConf sc=new SparkConf().setMaster("spark://localhost:7077").setAppName("Test");
        SparkContext context=new SparkContext(sc);

    }
}
