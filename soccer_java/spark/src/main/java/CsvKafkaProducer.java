import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.UUID;
import java.util.stream.Stream;

public class CsvKafkaProducer {
    private static String KafkaBrokerEndpoint = "localhost:9093";
    private static String KafkaTopic = "sensors";


    private Producer<String, String> ProducerProperties(){
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaBrokerEndpoint);
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "CLIENT");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        return new KafkaProducer<String, String>(properties);
    }

    public static void main(String[] args) throws URISyntaxException {

        CsvKafkaProducer kafkaProducer = new CsvKafkaProducer();
        kafkaProducer.PublishMessages();
    }



    private void PublishMessages() throws URISyntaxException{
        final Producer<String, String> playerProducer = ProducerProperties();
        loadData ld=new loadData();
        ArrayList<Player> players=ld.getPlayers();
        while (true){
            Iterator it=players.iterator();
            while (it.hasNext()){
                Player p=(Player) it.next();
                String data="id:"+p.getId()+",hb:"+((int) ((Math.random() * (180 - 150)) + 150));
                final ProducerRecord<String, String> playerRecord = new ProducerRecord<String, String>(
                        KafkaTopic, UUID.randomUUID().toString(), data);
                playerProducer.send(playerRecord, (metadata, exception) -> {
                    if(metadata != null){
                        System.out.println("CsvData: -> "+ playerRecord.key()+" | "+ playerRecord.value());
                    }
                    else{
                        System.out.println("Error Sending Csv Record -> "+ playerRecord.value());
                    }
                });
            }
        }
    }
}
