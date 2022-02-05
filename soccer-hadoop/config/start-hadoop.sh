#!/bin/bash

echo -e "start dfs..."

$HADOOP_HOME/sbin/start-dfs.sh

echo -e "start yarn"

$HADOOP_HOME/sbin/start-yarn.sh

# echo -e "open shell (bash)"

# sh -c service ssh start; bash
