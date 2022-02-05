#!/bin/bash

# stop hadoop master container
echo "stop hadoop-master container..."
docker rm -f hadoop-master 

# stop hadoop slave container
i=1
N=${1:-3}
while [ $i -lt $N ]
do
	echo "stop hadoop-slave$i container..."
	docker rm -f hadoop-slave$i
	i=$(( $i + 1 ))
done 

# remove network
echo "remove network bigdata-player-monitoring_default..."
docker network rm bigdata-player-monitoring_default
