package com.humuson.service;

import com.google.gson.Gson;
import com.humuson.agent.dto.AtReportDto;
import com.humuson.agent.dto.FtReportListDto;
import com.humuson.agent.dto.MtReportListDto;
import com.humuson.config.KafkaProducerConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProducerService {

    @Value(value = "${kafka.at.log.topic.name}")
    private String AT_REPORT_TOPIC_NAME;

    @Value(value = "${kafka.ft.log.topic.name}")
    private String FT_REPORT_TOPIC_NAME;

    @Value(value = "${kafka.mt.log.topic.name}")
    private String MT_REPORT_TOPIC_NAME;

    @Value(value = "${kafka.bootstrap.address}")
    private String BOOTSTRAP_SERVERS;

    private final KafkaProducerConfig kafkaProducerConfig;

    public void sendAtReportList(List<AtReportDto> atReportList) {

        KafkaProducer<String, String> producer = new KafkaProducer<>(kafkaProducerConfig.senderProps());

        Gson gson = new Gson();

        List<ProducerRecord<String, String>> produceFailList = new ArrayList<>();

        atReportList.forEach((at) -> {
            String data = gson.toJson(at);
            ProducerRecord<String, String> record = new ProducerRecord(AT_REPORT_TOPIC_NAME, data);
            try {
                producer.send(record);
                log.info("send to topic {}", data);
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

    public void sendFtReportList(List<FtReportListDto> ftMsgsLogList) {
        Properties configs = new Properties();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(configs);

        Gson gson = new Gson();
        ftMsgsLogList.stream().forEach((ft) -> {
            String data = gson.toJson(ft);
            ProducerRecord<String, String> record = new ProducerRecord(FT_REPORT_TOPIC_NAME, data);
            producer.send(record);
        });

        producer.flush();
        producer.close();
    }

    public void sendMtReportList(List<MtReportListDto> mtMsgsLogList) {
        Properties configs = new Properties();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(configs);

        Gson gson = new Gson();
        mtMsgsLogList.stream().forEach((mt) -> {
            String data = gson.toJson(mt);
            ProducerRecord<String, String> record = new ProducerRecord(MT_REPORT_TOPIC_NAME, data);
            producer.send(record);
        });

        producer.flush();
        producer.close();
    }


}
