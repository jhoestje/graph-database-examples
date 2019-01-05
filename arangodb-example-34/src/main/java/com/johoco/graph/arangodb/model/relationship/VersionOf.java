package com.johoco.graph.arangodb.model.relationship;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import com.johoco.graph.arangodb.model.ArtifactId;
import com.johoco.graph.arangodb.model.Version;

@Edge("versionOf")
public class VersionOf {

	@Id
	private String id;

	@From
	private Version version;

	@To
	private ArtifactId artifactId;

	public VersionOf(final Version from, final ArtifactId to) {
		this.version = from;
		this.artifactId = to;
	}

	// @Override
	// public String getEdgeName() {
	// return "versionOf";
	// }
}
