package com.johoco.graph.arangodb.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import com.johoco.graph.arangodb.model.Artifact;

public interface ArtifactGraphRepository extends ArangoRepository<Artifact, String> {

}
