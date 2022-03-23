import sys
import os
import subprocess

hadoop_home = os.environ['HADOOP_HOME']
hadoop_fs = '%s/bin/hadoop fs' % hadoop_home

from_path = "data/2022-03-21.csv"
to_path ='hdfs://localhost:9000/user/root/data/2022-03-21.csv'
command = f'%s -put {from_path} {to_path}' % hadoop_fs

sys.stdout.flush()
print(command)
subprocess.call(command, shell=True)
