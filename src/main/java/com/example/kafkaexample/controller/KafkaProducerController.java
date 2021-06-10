package com.example.kafkaexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.kafkaexample.model.User;

@RestController
public class KafkaProducerController {
	
	@Autowired
	private KafkaTemplate<String, Object> template;
	
	String topic = "ram";
	
	@GetMapping("/publish/{msg}")
	public String publishMessage(@PathVariable("msg") String msg)
	{
		template.send(topic, msg);
		
		return "Message published";
	}
	
	@GetMapping("/publishJSON")
	public String publishMessage()
	{
		User user = new User(100, "Ram", "ATP");
		template.send(topic, user);
		
		return "JSON Message published";
	}
}
