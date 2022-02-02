import org.apache.spark.{SparkConf, SparkContext}
object WasbIOTest {
  def main (arg: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("soccer-spark").setMaster("spark://127.0.0.1:7077")
    val sc = new SparkContext(conf)
    val rdd = sc.textFile("hdfs://172.22.0.2:9000/user/root/data/players-madrid.csv")
  }
}