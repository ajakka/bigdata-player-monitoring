# Big Data player monitoring

## Mustafa Ait Addi - Ajakka Abderrahim

## Put data into HDFS

```
hdfs dfs -mkdir -p data
hdfs dfs -put players-madrid.csv data
```

## Hadoop/Mapreduce

### Compiling java code with hadoop jar

"-d out" to specify where to put the .class files

```
javac -cp hadoop-client-api-3.3.1.jar \
			-d . \
			SoccerDriver.java SoccerMapper.java SoccerReducer.java

jar cfm soccer.jar Manifest.txt ./mapreduce/*.class
```

### HDFS Commands

```
./start-hadoop.sh

hdfs dfs -mkdir -p /root/data

hdfs dfs -put players-madrid.csv /root/data

hadoop jar soccer_map_reduce.jar /root/data /root/result

hdfs dfs -cat /root/result/part-00000
```
