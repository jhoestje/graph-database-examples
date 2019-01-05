package com.johoco.graph.arangodb.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import com.johoco.graph.arangodb.model.Classifier;

public interface ClassifierGraphRepository extends ArangoRepository<Classifier, String> {

}
