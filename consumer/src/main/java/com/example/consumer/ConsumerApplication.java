package com.example.consumer;

import java.util.ArrayList;
import java.util.List;

import com.example.producer.Mensagem;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@RequestMapping("consumer")
public class ConsumerApplication {
	
	private List<Mensagem> messages = new ArrayList<>(10);
	
	@KafkaListener(topics = "meu-topico", groupId = "channel1")
	public void listen(ConsumerRecord<String, Mensagem> record) throws Exception {
		messages.add(record.value());
	}

	@GetMapping("/messages")
	public ResponseEntity<List<Mensagem>> messages() {
		return ResponseEntity.ok(this.messages);
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}
}