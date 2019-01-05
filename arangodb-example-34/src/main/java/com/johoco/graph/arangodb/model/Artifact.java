package com.johoco.graph.arangodb.model;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Transient;

import com.arangodb.springframework.annotation.Document;
import com.johoco.graph.arangodb.model.api.ArtifactApiModel;

import lombok.Data;

@Data
// override the collection name to 'artifacts' from the default classname
// 'Artifact'
@Document("artifacts")
public class Artifact {
	private DateTime createdDateTime;
	@Transient
	private GroupId groupId;
	@Transient
	private ArtifactId artifactId;
	@Transient
	private Version version;
	@Transient
	private Classifier classifier;
	@Transient 
	private Packaging packaging;
	
}
