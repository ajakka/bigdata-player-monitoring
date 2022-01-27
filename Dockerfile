FROM ubuntu:20.04

RUN apt-get update && 
RUN	apt-get install -y \
    curl \
		nano \
		python3 \
		python3-pip \
		openjdk-8-jdk 
		# net-tools \
		# netcat \
		# gnupg

ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64/

WORKDIR /football

# CMD [ "echo", "SUCCESS" ]