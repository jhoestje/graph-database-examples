package com.johoco.graph.arangodb.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import com.johoco.graph.arangodb.model.ArtifactId;

public interface ArtifactIdGraphRepository extends ArangoRepository<ArtifactId, String> {

}
