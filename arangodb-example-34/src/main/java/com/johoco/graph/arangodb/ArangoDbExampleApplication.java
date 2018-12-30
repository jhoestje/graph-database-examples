package com.johoco.graph.arangodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.johoco.graph.arangodb")
public class ArangoDbExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArangoDbExampleApplication.class, args);
	}
}
