package com.johoco.graph.arangodb.model;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.Document;

import lombok.Data;

@Data
@Document("groupIds")
public class GroupId {
	@Id
	private String id;
	private String value;
}
