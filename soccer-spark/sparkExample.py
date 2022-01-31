from pyspark import SparkContext, SparkConf, SparkFiles
from pyspark.sql import SparkSession

sc = SparkContext.getOrCreate(SparkConf().setMaster('spark://localhost:7077'))
# sc.setLogLevel("FATAL")

spark = SparkSession \
    .builder \
    .appName("Football players spark instance") \
    .getOrCreate()

spark.sparkContext.addFile('https://raw.githubusercontent.com/ajakka/bigdata-player-monitoring/main/dataset/players_fifa21_realmadrid.csv')

# Load csv data
spk_df = spark.read \
		.csv(f"file://{SparkFiles.get('players_fifa21_realmadrid.csv')}", header=True, inferSchema= True)

# count players by club name
club_name_count = spk_df.groupBy("club_name").count()
club_name_count.show()

# print(df.head())



sc.stop()