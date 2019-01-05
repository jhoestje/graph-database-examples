package com.johoco.graph.arangodb.model.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArtifactApiModel {
	private String groupId;
	private String artifactId;
	private String version;
	private String classifier;
	private String packaging;
}
