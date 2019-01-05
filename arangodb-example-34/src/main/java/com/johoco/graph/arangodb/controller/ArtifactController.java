package com.johoco.graph.arangodb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johoco.graph.arangodb.converter.ArtifactConverter;
import com.johoco.graph.arangodb.model.Artifact;
import com.johoco.graph.arangodb.model.api.ArtifactApiModel;
import com.johoco.graph.arangodb.service.ArtifactService;

@RestController
@RequestMapping(path = "/artifacts")
public class ArtifactController {

	private ArtifactService artifactService;

	public ArtifactController(final ArtifactService artifactService) {
		this.artifactService = artifactService;
	}

	//todo:  change to '/' and post
	@PostMapping("/save")
	public void save(final ArtifactApiModel artifactApiModel) {
		Artifact artifact = ArtifactConverter.convert(artifactApiModel);
		this.artifactService.save(artifact);
	}
	
	@GetMapping("/a")
	public void save() {
		ArtifactApiModel artifactApiModel = new ArtifactApiModel();
		artifactApiModel.setArtifactId("art1");
		artifactApiModel.setGroupId("group1");
		artifactApiModel.setVersion("1.0.0");
		artifactApiModel.setClassifier("");
		artifactApiModel.setPackaging("JAR");
		Artifact artifact = ArtifactConverter.convert(artifactApiModel);
		this.artifactService.save(artifact);
	}
}
