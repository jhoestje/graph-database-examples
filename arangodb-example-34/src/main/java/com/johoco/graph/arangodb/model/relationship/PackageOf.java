package com.johoco.graph.arangodb.model.relationship;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import com.johoco.graph.arangodb.model.ArtifactId;
import com.johoco.graph.arangodb.model.GroupId;

@Edge("packageOf")
public class PackageOf {

	@Id
	private String id;

	@From
	private ArtifactId artifactId;

	@To
	private GroupId groupId;

	public PackageOf(final ArtifactId from, final GroupId to) {
		super();
		this.artifactId = from;
		this.groupId = to;
	}

//	@Override
//	public String getEdgeName() {
//		return "packageOf";
//	}

}
