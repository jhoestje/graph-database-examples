package com.johoco.graph.arangodb.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import com.johoco.graph.arangodb.model.relationship.VersionOf;

public interface VersionOfGraphRepository extends ArangoRepository<VersionOf, String> {

}
