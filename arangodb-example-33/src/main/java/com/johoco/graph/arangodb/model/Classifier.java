package com.johoco.graph.arangodb.model;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.Document;

import lombok.Data;

@Data
@Document("classifiers")
public class Classifier {
	@Id
	private String id;
	private String value;
}
