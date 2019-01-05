package com.johoco.graph.arangodb.model;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.Document;

import lombok.Data;

@Data
@Document("versions")
public class Version {
	@Id
	private String id;
	private String value;
}
