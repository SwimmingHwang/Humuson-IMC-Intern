package com.humuson.api;

import com.google.gson.Gson;
import com.humuson.agent.dto.AtMsgsSaveRequestDto;
import com.humuson.api.config.KafkaProducerConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.clients.producer.internals.DefaultPartitioner;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Properties;

@Slf4j
@Component
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    private static String AT_TOPIC_NAME;
    private static String MT_TOPIC_NAME;
    private static String BOOTSTRAP_SERVERS;

    @Value("${kafka.bootstrap.address}")
    public void setBootstrapServers(String address) {
        BOOTSTRAP_SERVERS = address;
    }
    @Value("${kafka.at.topic.name}")
    public void setTopicAtName(String topicName){
        AT_TOPIC_NAME = topicName;
    }
    @Value("${kafka.mt.topic.name}")
    public void setTopicMtName(String topicName){
        MT_TOPIC_NAME = topicName;
    }


    public static String produce(String args, int topicIdx) {
        // TODO : String[] stream으로 처리할 것 .
        // TODO : 데이터 유효성 검사하는 로직 추가할 것.
        Properties configs = new Properties();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(configs);

        String topicName = "";
        if (topicIdx==0){
            topicName = AT_TOPIC_NAME;
        }
        else if (topicIdx == 1){
            topicName = MT_TOPIC_NAME;
        }

        String stringStatusCode = "";
//        for (int index = 0; index < args.length; index++) {
        try {
            String data = args + "";
            ProducerRecord<String, String> record = new ProducerRecord<>(topicName, data);

            producer.send(record);

            log.info("Send to " + topicName + " | data : " + data);
            stringStatusCode = "200";
        } catch (Exception e) {
            log.info(e.getMessage(),e);
            stringStatusCode = "9000";
        } finally{
            producer.flush();
            producer.close();
        }

        return stringStatusCode;
    }


    public static String batchProduce(List<AtMsgsSaveRequestDto> atMsgsSaveRequestDtos, int topicIdx) {
        // TODO : String[] stream으로 처리할 것 .
        // TODO : 데이터 유효성 검사하는 로직 추가할 것.

        KafkaProducerConfig kafkaProducerConfig = new KafkaProducerConfig();
        logger.debug("BATCH PRODUCE");
        logger.info("BATCH PRODUCE");


        KafkaProducer<String, String> producer = new KafkaProducer<>(kafkaProducerConfig.senderProps());

        String topicName = "";
        if (topicIdx==0){
            topicName = AT_TOPIC_NAME;
        }
        else if (topicIdx == 1){
            topicName = MT_TOPIC_NAME;
        }

        String stringStatusCode = "";
        Gson gson = new Gson();

        for (AtMsgsSaveRequestDto atMsgsSaveRequestDto : atMsgsSaveRequestDtos) {
            try {
                String reqDataJson = gson.toJson(atMsgsSaveRequestDto);

                String data = reqDataJson + "";
                ProducerRecord<String, String> record = new ProducerRecord<>(topicName, data);

                producer.send(record);
                logger.debug("Send to " + topicName + " | data : " + data);
                log.info("Send to " + topicName + " | data : " + data);
                stringStatusCode = "200";
            } catch (Exception e) {
                log.info(e.getMessage(), e);
                stringStatusCode = "9000";
            }
        }
        producer.flush();
        producer.close();
        return stringStatusCode;
    }
}

