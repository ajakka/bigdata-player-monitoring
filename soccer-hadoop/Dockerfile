FROM ubuntu:20.04

WORKDIR /hadoop/data

RUN apt-get update 
RUN	apt-get install -y openssh-server openjdk-8-jdk nano wget

# install hadoop
ENV HADOOP_VERSION 3.2.2
RUN wget https://www.apache.org/dist/hadoop/common/hadoop-$HADOOP_VERSION/hadoop-$HADOOP_VERSION.tar.gz 
RUN tar -xzvf hadoop-$HADOOP_VERSION.tar.gz 
RUN mv hadoop-$HADOOP_VERSION /usr/local/hadoop 
RUN rm hadoop-$HADOOP_VERSION.tar.gz

# set environment variable
ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
ENV HADOOP_HOME=/usr/local/hadoop 
ENV PATH=$PATH:/usr/local/hadoop/bin:/usr/local/hadoop/sbin 

# ssh without key
RUN ssh-keygen -t rsa -f ~/.ssh/id_rsa -P '' && \
		cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys

# make necessary directories
RUN mkdir -p ~/hdfs/namenode && \ 
    mkdir -p ~/hdfs/datanode && \
    mkdir $HADOOP_HOME/logs

ARG PARENT_FOLDER
# copy ssh config
COPY $PARENT_FOLDER/config/ssh_config ~/.ssh/config 

# copy site config for hadoop
COPY $PARENT_FOLDER/config/hdfs-site.xml $HADOOP_HOME/etc/hadoop/hdfs-site.xml
COPY $PARENT_FOLDER/config/core-site.xml $HADOOP_HOME/etc/hadoop/core-site.xml 
COPY $PARENT_FOLDER/config/yarn-site.xml $HADOOP_HOME/etc/hadoop/yarn-site.xml 
COPY $PARENT_FOLDER/config/mapred-site.xml $HADOOP_HOME/etc/hadoop/mapred-site.xml 

#COPY config/slaves $HADOOP_HOME/etc/hadoop/slaves 
COPY config/hadoop-env.sh /usr/local/hadoop/etc/hadoop/hadoop-env.sh 
COPY config/start-hadoop.sh ~/start-hadoop.sh
#COPY config/run-wordcount.sh ~/run-wordcoun.sh

 RUN chmod +x ~/start-hadoop.sh && \
#     chmod +x ~/run-wordcount.sh && \
     chmod +x $HADOOP_HOME/sbin/start-dfs.sh && \
     chmod +x $HADOOP_HOME/sbin/start-yarn.sh 

# format namenode
RUN /usr/local/hadoop/bin/hdfs namenode -format

CMD [ "sh", "-c", "service ssh start; bash"]

