package com.example.kafkaexample.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.example.kafkaexample.model.User;

@Configuration
public class KafkaConsumerConfig {

	// Config for String plain text

	@Bean
	public ConsumerFactory<String, String> consumerFactory()
	{
		Map<String, Object> configs = new HashMap<String, Object>();
		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configs.put(ConsumerConfig.GROUP_ID_CONFIG, "ram-1");

		return new DefaultKafkaConsumerFactory<String, String>(configs);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory()
	{
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
		factory.setConsumerFactory(consumerFactory());

		return factory;
	}

	// Config for JSON object

	@Bean
	public ConsumerFactory<String, User> userConsumerFactory()
	{
		Map<String, Object> configs = new HashMap<String, Object>();
		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		configs.put(ConsumerConfig.GROUP_ID_CONFIG, "ram-2");

		return new DefaultKafkaConsumerFactory<String, User>(configs, new StringDeserializer(), new JsonDeserializer<>(User.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, User> userKafkaListenerContainerFactory()
	{
		ConcurrentKafkaListenerContainerFactory<String, User> factory = new ConcurrentKafkaListenerContainerFactory<String, User>();
		factory.setConsumerFactory(userConsumerFactory());

		return factory;
	}
}
