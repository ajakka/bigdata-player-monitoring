echo -e "\033[0;32m[STEP 1/5] START DFS \033[1;37m"
$HADOOP_HOME/sbin/start-dfs.sh

echo -e "\033[0;32m[STEP 2/5] START YARN \033[1;37m"
$HADOOP_HOME/sbin/start-yarn.sh

echo -e "\033[0;32m[STEP 3/5] COPY DATA \033[1;37m"
hdfs dfs -mkdir -p /user/root/data
hdfs dfs -put players-madrid.csv /user/root/data

echo -e "\033[0;32m[STEP 4/5] RUN MAPREDUCE \033[1;37m"
hadoop jar soccer_map_reduce.jar /user/root/data /user/root/result

echo -e "\033[0;32m[STEP 5/5] RESULTS \033[1;37m"
hdfs dfs -cat /user/root/result/part-00000