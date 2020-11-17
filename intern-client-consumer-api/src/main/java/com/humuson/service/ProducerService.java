package com.humuson.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.humuson.domain.msgslog.AtMsgsLog;
import com.humuson.domain.msgslog.AtMsgsLogRepository;
import com.humuson.dto.AtMsgsLogDto;
import com.humuson.dto.AtMsgsLogListDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProducerService {

    private static String TOPIC_NAME = "atMsgsLogTopic";
    private static String BOOTSTRAP_SERVERS = "localhost:9092";

    private final AtMsgsLogRepository atMsgsLogRepository;

    public void sendatMsgsLogList(List<AtMsgsLogListDto> atMsgsLogList) {
        Properties configs = new Properties();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        atMsgsLogList.stream().forEach((at) -> {
            KafkaProducer<String, Object> producer = new KafkaProducer<>(configs);
            ObjectMapper objectMapper = new ObjectMapper();
            String json = "{ \"id\" : \""+at.getId()+"\", " +
                    "\"status\" : \""+at.getStatus()+"\", " +
                    "\"priority\" : \""+at.getPriority()+"\", " +
                    "\"reserved_date\" : \""+at.getReserved_date()+"\", " +
                    "\"senderkey\" : \""+at.getSenderkey()+"\", " +
                    "\"phone_number\" : \""+at.getPhone_number()+"\", " +
                    "\"template_code\" : \""+at.getTemplate_code()+"\", " +
                    "\"message\" : \""+at.getMessage()+"\", " +
                    "\"etc1\" : \""+at.getEtc1()+"\", " +
                    "\"etc2\" : \""+at.getEtc2()+"\", " +
                    "\"etc3\" : \""+at.getEtc3()+"\"" +
                    " }";
            try {
                AtMsgsLogListDto atObject = objectMapper.readValue(json, AtMsgsLogListDto.class);

                ProducerRecord<String, Object> record = new ProducerRecord(TOPIC_NAME, atObject);
                producer.send(record);

//                saveEtc1Status(at.getId());

                producer.flush();
                producer.close();

            } catch (IOException e) {
                log.info("error : {}", e.toString());
            }

        });


    }


}
