package com.johoco.graph.arangodb.config;

import org.springframework.context.annotation.Configuration;

import com.arangodb.ArangoDB;
import com.arangodb.ArangoDB.Builder;
import com.arangodb.springframework.annotation.EnableArangoRepositories;
import com.arangodb.springframework.config.AbstractArangoConfiguration;

@Configuration
@EnableArangoRepositories(basePackages = { "com.johoco.graph.arangodb" })
public class RepoConfiguration extends AbstractArangoConfiguration {
	@Override
	public Builder arango() {
		// return new ArangoDB.Builder().host("localhost",
		// 8529).user("root").password(null);
		return new ArangoDB.Builder();
	}

	@Override
	public String database() {
		return "graphDemo";
	}
}
