package com.johoco.graph.arangodb.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import com.johoco.graph.arangodb.model.Version;

public interface VersionGraphRepository extends ArangoRepository<Version, String> {

}
