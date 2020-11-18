package com.humuson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.humuson.domain.msgs.AtMsgs;
import com.humuson.service.AtMsgsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Slf4j
//@RestController
public class AgentConsumerApplication {

//    private static final Logger logger = LoggerFactory.getLogger(AgentConsumerApplication.class);

    @Autowired
    private static AtMsgsService atMsgsService;

    private static String TOPIC_NAME = "mytopic";
    private static String BOOTSTRAP_SERVERS = "localhost:9092";
    private static String GROUP_ID = "atMsgs-group";

    public static void main(String[] args) throws InterruptedException {
        Properties configs = new Properties();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(configs);

        consumer.subscribe(Arrays.asList(TOPIC_NAME));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
            List<AtMsgs> atMsgsList = new ArrayList<>();
            for (ConsumerRecord<String, String> record : records) {
                String data = record.toString();
                log.info("data is : {}", data);
                try {
                    Gson gson = new Gson();
                    AtMsgs atMsgs = gson.fromJson(data, AtMsgs.class);
                    log.info("atmsgs is : {}", atMsgs.getMessage());
                    if(!atMsgs.equals(null)) {
                        log.info("add msg");
                        atMsgsList.add(atMsgs);
                        atMsgsService.save(atMsgs);
                    } else {
                        log.info("null");
                    }
                } catch (Exception e) {

                }
                log.info("Consume From {} | data : {}" , TOPIC_NAME,  data);
            }
            if(atMsgsList.size() != 0) {
//                atMsgsService.saveAll(atMsgsList);
                log.info("save done");
            }
            else {
                log.info("cant save");
            }
            Thread.sleep(5000);
        }
    }

}
