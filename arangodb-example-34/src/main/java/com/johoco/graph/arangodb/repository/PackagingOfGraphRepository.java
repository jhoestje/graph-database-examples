package com.johoco.graph.arangodb.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import com.johoco.graph.arangodb.model.ArtifactId;
import com.johoco.graph.arangodb.model.relationship.ArtifactIdOf;
import com.johoco.graph.arangodb.model.relationship.PackagingOf;

public interface PackagingOfGraphRepository extends ArangoRepository<PackagingOf, String> {

}
