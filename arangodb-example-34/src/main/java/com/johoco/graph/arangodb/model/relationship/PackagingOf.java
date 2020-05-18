package com.johoco.graph.arangodb.model.relationship;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import com.johoco.graph.arangodb.model.ArtifactId;
import com.johoco.graph.arangodb.model.Packaging;
import com.johoco.graph.arangodb.model.Version;

import lombok.Data;

@Data
@Edge("packageOf")
public class PackagingOf {

	@Id
	private String id;

	@From
	private Packaging artifactId;

	@To
	private Version groupId;

	public PackagingOf(final Packaging from, final Version to) {
		super();
		this.artifactId = from;
		this.groupId = to;
	}

	public static String getEdgeName() {
		return "packageOf";
	}

}
