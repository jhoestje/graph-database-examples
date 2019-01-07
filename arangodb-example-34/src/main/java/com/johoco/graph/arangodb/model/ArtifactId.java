package com.johoco.graph.arangodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.HashIndex;
import com.arangodb.springframework.annotation.Relations;
import com.johoco.graph.arangodb.model.relationship.ArtifactIdOf;

import lombok.Data;

@Data
@HashIndex(fields = { "value" }, unique = true)
// override the collection name to 'artifactIds' from the default classname
// 'ArtifactId'
@Document("artifactIds")
public class ArtifactId {
	@Id
	private String id;
//	@Key
//	private String key;
	@Version
	
	private String value;
	@Relations(edges = ArtifactIdOf.class, lazy = true)
	private GroupId groupId;
	
	public static String getVertexName() {
		return "artifactIds";
	}
}
