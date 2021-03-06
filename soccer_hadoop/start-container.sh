#!/bin/bash

# create network
echo "create network bigdata-player-monitoring_default..."
docker network rm bigdata-player-monitoring_default &> /dev/null
docker network create bigdata-player-monitoring_default &> /dev/null

# start hadoop master container
docker rm -f hadoop-master &> /dev/null
echo "start hadoop-master container..."
docker run -itd \
	--net=bigdata-player-monitoring_default \
  	-p 9870:9870 \
  	-p 8088:8088 \
  	-p 9000:9000 \
	-p 8032:8032 \
	--name hadoop-master \
	--hostname hadoop-master \
	soccer/hadoop &> /dev/null

# start hadoop slave container
i=1
N=${1:-3}
while [ $i -lt $N ]
do
	docker rm -f hadoop-slave$i &> /dev/null
	echo "start hadoop-slave$i container..."
	docker run -itd \
		--net=bigdata-player-monitoring_default \
	  --name hadoop-slave$i \
	  --hostname hadoop-slave$i \
	  soccer/hadoop &> /dev/null
	i=$(( $i + 1 ))
done 

# get into hadoop master container
docker exec -it hadoop-master bash
