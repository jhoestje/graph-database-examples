package com.johoco.graph.arangodb.model.relationship;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import com.johoco.graph.arangodb.model.ArtifactId;
import com.johoco.graph.arangodb.model.GroupId;

@Edge("artifactIdOf")
public class ArtifactIdOf implements GraphRelationship {

	@Id
	private String id;

	@From
	private ArtifactId artifactId;

	@To
	private GroupId groupId;

	public ArtifactIdOf(final ArtifactId from, final GroupId to) {
		this.artifactId = from;
		this.groupId = to;
	}

	@Override
	public String getEdgeName() {
		return "artifactIdOf";
	}

}
