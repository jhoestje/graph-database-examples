package com.johoco.graph.arangodb.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import com.johoco.graph.arangodb.model.Packaging;

public interface PackagingGraphRepository extends ArangoRepository<Packaging, String> {

}
