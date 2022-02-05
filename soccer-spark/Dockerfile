FROM bitnami/spark:3.2.0

USER root
RUN pip3 install py4j

COPY soccer-spark/players-madrid.csv /opt/bitnami/spark/players-madrid.csv
COPY soccer-spark/sparkExample.py /opt/bitnami/spark/sparkExample.py