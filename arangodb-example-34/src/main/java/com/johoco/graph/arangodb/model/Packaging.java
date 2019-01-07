package com.johoco.graph.arangodb.model;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.Document;

import lombok.Data;

@Data
@Document("packages")
public class Packaging {
	@Id
	private String id;
	private String value;

	public static String getVertexName() {
		return "packages";
	}
}
