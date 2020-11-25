package com.humuson.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.internals.DefaultPartitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaProducerConfig {

    @Value(value = "${kafka.bootstrap.address}")
    private String bootStrapAddress;

    @Bean
    public ProducerFactory<String, byte[]> producerFactory() {
        return new DefaultKafkaProducerFactory<>(senderProps());
    }

    public Map<String, Object> senderProps() {
        Map<String, Object> props = new HashMap<>();
        // TODO : 값 박지 말 것.
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapAddress);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // batch.size: 같은 파티션으로 보내는 여러 데이터를 함께 배치로 보내기 위한 사이즈. 정의된 크기보다 큰 데이터는 배치를 시도하지 않음.
        // (bytes, default: 16384, 16KB)
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 20000); //20 Kbyte
        // 일괄 처리 지연에 대한 상한
        props.put(ProducerConfig.LINGER_MS_CONFIG, 200);
        //max.request.size: 프로듀서가 보낼 수 있는 최대 메시지 사이즈. (default: 1MB)
        props.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, 22 * 1024 * 1024); // 22 Mbyte
        // 메시지 전송이 실패했을시 예외를 발생시키기전 재전송 시도 값
        props.put(ProducerConfig.RETRIES_CONFIG, 1);
        // 프로듀서가 전송한 메시지 카프카가 잘 받은걸로 처리하는 기준
        props.put(ProducerConfig.ACKS_CONFIG, "all"); //all 느림 : leader follwer모두 받았는지 확인
        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, SimplePartitional.class);
        return props;
    }

    /**
     * kafka partitioning 전략
     */
    public static class SimplePartitional extends DefaultPartitioner {

        private int num;

        @Override
        public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
            List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
            return (num++ + 1) % partitions.size();
        }
    }
    @Bean
    public KafkaTemplate<String, byte[]> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}
