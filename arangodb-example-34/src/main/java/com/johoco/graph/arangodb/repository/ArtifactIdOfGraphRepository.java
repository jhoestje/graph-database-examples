package com.johoco.graph.arangodb.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import com.johoco.graph.arangodb.model.ArtifactId;
import com.johoco.graph.arangodb.model.relationship.ArtifactIdOf;

public interface ArtifactIdOfGraphRepository extends ArangoRepository<ArtifactIdOf, String> {

}
