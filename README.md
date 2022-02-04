# Big Data player monitoring

## Mustafa Ait Addi - Ajakka Abderrahim

## Docker

### See running containers

`$ docker ps`

### Build an image from a Dockerfile

(-t myimage) is the image tag

(.) is the path to Dockerfile

```
$ docker build -t myimage .
```

### Run an image

```
$ docker run myimage
```

### Check for running processes

```
$ docker ps -a
```

### List installed images

```
$ docker images

REPOSITORY     TAG     IMAGE ID       CREATED         SIZE
bitnami/node   12.3.1  b5d16fe457eb   5 weeks ago     720.8MB
bitnami/spark  3       55b41198c55e   2 weeks ago     1.67GB
```

### Remove installed images

(55b41198c55e) is the image id

```
$ docker image rm 55b41198c55e

Untagged: bitnami/spark:3
Untagged: bitnami/spark@sha256:c6591ea33ab634d72fdeb56d8adc61483bb3d509efda5ea0fd4fbc4458fbc9e1
```

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
