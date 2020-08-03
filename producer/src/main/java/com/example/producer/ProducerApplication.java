package com.example.producer;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@RequestMapping("producer")
public class ProducerApplication {

	@Autowired
	private KafkaTemplate<String, Mensagem> template;

	@GetMapping(value = "/send/{msg}")
	public void send(@PathVariable String msg) throws Exception {
		this.template.send("meu-topico", LocalDate.now().toString() , new Mensagem(msg));
	}

	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}
}
