package com.johoco.graph.arangodb.service;

import org.springframework.stereotype.Service;

import com.johoco.graph.arangodb.model.Artifact;
import com.johoco.graph.arangodb.repository.ArtifactIdGraphRepository;
import com.johoco.graph.arangodb.repository.ArtifactRepository;
import com.johoco.graph.arangodb.repository.GroupIdGraphRepository;
import com.johoco.graph.arangodb.repository.VersionGraphRepository;

@Service
public class ArtifactService {

	private ArtifactRepository artifactRepo;

	public ArtifactService(final ArtifactRepository artifactRepo, final ArtifactIdGraphRepository artifactIdRepo,
			final GroupIdGraphRepository groupIdRepo, final VersionGraphRepository graphRepo) {
		this.artifactRepo = artifactRepo;
	}
	
	public void save(final Artifact artifact) {
		artifactRepo.save(artifact);
	}

}
