package com.example.kafkaexample.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kafkaexample.model.User;

@RestController
public class KafkaConsumerController {
	
	List<String> msgs = new ArrayList<String>();
	
	User userTopic = null;

	@KafkaListener(groupId = "ram-1", topics = "ram", containerFactory = "kafkaListenerContainerFactory")
	public List<String> getMsgsFromTopic(String msg)
	{
		msgs.add(msg);
		return msgs;
	}
	
	@KafkaListener(groupId = "ram-2", topics = "ram", containerFactory = "userKafkaListenerContainerFactory")
	public User getJSONMessageFromTopic(User user)
	{
		userTopic = user;
		return userTopic;
	}
	
	@GetMapping("/consumeMessages")
	public List<String> getMessages()
	{
		return msgs;
	}
	
	@GetMapping("/consumeJSONMessages")
	public User getJSONMessages()
	{
		return userTopic;
	}
}
