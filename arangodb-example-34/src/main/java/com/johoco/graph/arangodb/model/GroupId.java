package com.johoco.graph.arangodb.model;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.HashIndex;

import lombok.Data;

@Data
// override the collection name to 'groupIds' from the default classname
// 'GroupIdf'
@Document("groupIds")
@HashIndex(fields = { "value" }, unique = true)
public class GroupId {
	@Id
	private String id;
	private String value;
//	@Relations(edges = ArtifactIdOf.class, lazy = true)
//	private Collection<GroupId> artifactIds;
	
	public static String getVertexName() {
		return "groupIds";
	}
}
