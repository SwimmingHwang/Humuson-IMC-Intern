package com.humuson.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.util.backoff.FixedBackOff;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value("${kafka.bootstrap.address}")
    private String bootstrapServers;

    @Value("${kafka.consumer.group.id}")
    private String consumerGroupid;

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupid);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

//        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
//        props.put(ConsumerConfig.RETRY_BACKOFF_MS_CONFIG, 5000);

        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 15000);
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 10000); // 한번에 가져올 수 있는 메세지 최대 사이즈
        props.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, 22 * 1024 * 1024);
        props.put(ConsumerConfig.FETCH_MAX_BYTES_CONFIG, 50 * 1024 * 1024);
        props.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, 500);
        props.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, 50 * 1024 * 1024);

        return props;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }


    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setRetryTemplate(retryTemplate());
        factory.setRecoveryCallback(context -> {
            log.info(" In recovery callback method ");
            return null;
        });
//        factory.setBatchListener(true);
        return factory;
    }

    /*@Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setMessageConverter(new StringJsonMessageConverter());
        factory.setConsumerFactory(consumerFactory());
        factory.setRetryTemplate(retryTemplate());
        return factory;
    }*/

    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();

        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(10000); // 실패 시 다시 시작 시간 (10초)
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);

        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(720); // 실패 시 720 다시 시도 -> 2시간 지속
        retryTemplate.setRetryPolicy(retryPolicy);

        return retryTemplate;
    }

}