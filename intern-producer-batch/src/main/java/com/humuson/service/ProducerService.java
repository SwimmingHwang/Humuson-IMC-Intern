package com.humuson.service;

import com.google.gson.Gson;
import com.humuson.agent.dto.AtReportDto;
import com.humuson.agent.dto.FtReportListDto;
import com.humuson.agent.dto.MtReportListDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;

@Slf4j
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

    public void sendAtReportList(List<AtReportDto> atReportList) {
        Properties configs = new Properties();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(configs);

        Gson gson = new Gson();

        atReportList.forEach((at) -> {
            String data = gson.toJson(at);
            ProducerRecord<String, String> record = new ProducerRecord(AT_REPORT_TOPIC_NAME, data);
            producer.send(record);
            log.info("send to topic {}", data);
        });

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
