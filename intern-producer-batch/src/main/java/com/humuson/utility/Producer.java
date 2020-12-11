package com.humuson.utility;

import com.google.gson.Gson;
import com.humuson.agent.dto.*;
import com.humuson.config.KafkaProducerConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Slf4j
@RequiredArgsConstructor
@Component
public class Producer {

    @Value(value = "${kafka.bootstrap.address}")
    private String BOOTSTRAP_SERVERS;

    @Value(value = "${kafka.at.log.topic.name}")
    private String AT_REPORT_TOPIC_NAME;

    @Value(value = "${kafka.mt.log.topic.name}")
    private String MT_REPORT_TOPIC_NAME;

    @Value(value = "${kafka.health.check.topic.name}")
    private String HC_TOPIC_NAME;

    private final KafkaProducerConfig kafkaProducerConfig;

    public String kafkaHealthCheckProduce() {
        try {
            Properties configs = new Properties();
            configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
            configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
            configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

            KafkaProducer<String, String> producer = new KafkaProducer<>(configs);

            ProducerRecord<String, String> record = new ProducerRecord<>(HC_TOPIC_NAME, "1");
            try {
                producer.send(record);
            } catch (Exception e) {
                return "9000";
            }

            producer.flush();
            producer.close();
            return "200";
        } catch (Exception e) {
            return "9999";
        }
    }

    public void sendAtReportList(List<AtReportSaveRequestDto> atReportList) {

        KafkaProducer<String, String> producer = new KafkaProducer<>(kafkaProducerConfig.senderProps());

        Gson gson = new Gson();

        List<ProducerRecord<String, String>> produceFailList = new ArrayList<>();

        atReportList.forEach((at) -> {
            String data = gson.toJson(at);
            ProducerRecord<String, String> record = new ProducerRecord(AT_REPORT_TOPIC_NAME, data);
            try {
                producer.send(record);
                log.info("Send to AT topic {}", data);
            } catch (Exception e) {
                produceFailList.add(record);
                log.info("e.getMessage()",e);
            }
        });

        log.info("Number of Producer Fail Record :" + produceFailList.size());
        try {
            for(ProducerRecord<String, String> failRecode : produceFailList)
                producer.send(failRecode);
        } catch(Exception e){
            log.info(e.getMessage(), e);
        }

        producer.flush();
        producer.close();
    }


    public void sendMtReportList(List<MtReportSaveRequestDto> mtReportList) {

        KafkaProducer<String, String> producer = new KafkaProducer<>(kafkaProducerConfig.senderProps());

        Gson gson = new Gson();

        List<ProducerRecord<String, String>> produceFailList = new ArrayList<>();

        mtReportList.forEach((mt) -> {
            String data = gson.toJson(mt);
            ProducerRecord<String, String> record = new ProducerRecord(MT_REPORT_TOPIC_NAME, data);
            try {
                producer.send(record);
                log.info("Send to  MT topic {}", data);
            } catch (Exception e) {
                produceFailList.add(record);
                log.info("e.getMessage()",e);
            }
        });

        log.info("Number of Producer Fail Record :" + produceFailList.size());
        try {
            for(ProducerRecord<String, String> failRecode : produceFailList)
                producer.send(failRecode);
        } catch(Exception e){
            log.info(e.getMessage(), e);
        }

        producer.flush();
        producer.close();
    }



}
