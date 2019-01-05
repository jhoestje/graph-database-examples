package com.johoco.graph.arangodb.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import com.johoco.graph.arangodb.model.GroupId;

public interface GroupIdGraphRepository extends ArangoRepository<GroupId, String> {

}
