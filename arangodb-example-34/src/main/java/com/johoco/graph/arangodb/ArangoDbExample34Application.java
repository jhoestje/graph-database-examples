package com.johoco.graph.arangodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import com.arangodb.springframework.core.ArangoOperations;
import com.johoco.graph.arangodb.repository.GroupIdGraphRepository;

@SpringBootApplication
@ComponentScan("com.johoco.graph.arangodb")
@ImportResource({ "classpath*:queries/queries.arango.xml" })
public class ArangoDbExample34Application {
	
	@Autowired
	private GroupIdGraphRepository repo;
	@Autowired
	private ArangoOperations operations;

	public static void main(String[] args) {
		SpringApplication.run(ArangoDbExample34Application.class, args);
	}
}
