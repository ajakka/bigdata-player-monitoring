import org.apache.spark.sql.*;
import org.apache.spark.sql.types.StructType;

import javax.xml.crypto.Data;
import java.lang.System;
import java.util.ArrayList;
import java.util.Arrays;

public class loadData {
    public static void main(String[] args) {
        loadData ld=new loadData();
        SparkSession spark=ld.getSession();
        Dataset<Row> players=ld.getDataSet();
        players.createOrReplaceTempView("Players");
        Dataset<Row> countedPlayers=spark.sql("select nationality,count(nationality) from Players group by nationality");
        //countedPlayers.show();
        ArrayList<Player> playersList= ld.getPlayers();
        playersList.remove(0);
        System.out.println(playersList.get(0).getLong_name());

    }
    public SparkSession getSession(){
        SparkSession spark = SparkSession.builder().appName("kafkaStream").master("spark://SVE1511Z1EB:7077")
                .getOrCreate();
        return spark;
    }
    public Dataset<Row> getDataSet(){
        StructType schema=new StructType().add("id","Integer")
                .add("player_url","String")
                .add("short_name","String")
                .add("long_name","String")
                .add("age","Integer")
                .add("dob","String")
                .add("height_cm","String")
                .add("weight_kg","String")
                .add("nationality","String")
                .add("value_eur","String");
        Dataset<Row> players=getSession().read().option("header",false).schema(schema)
                .csv("hdfs://172.22.0.2:9000/user/root/data/players-madrid.csv");
        return players;
    }

    public ArrayList<Player> getPlayers(){
        Dataset<Row> data=getDataSet();
        Dataset<Player> players=data.as(Encoders.bean(Player.class));
        return new ArrayList<Player>(Arrays.asList((Player[])players.collect()));
    }
}
