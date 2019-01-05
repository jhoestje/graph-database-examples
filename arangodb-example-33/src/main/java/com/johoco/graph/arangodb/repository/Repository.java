package com.johoco.graph.arangodb.repository;

import java.util.Map;

public interface Repository {
	void queries(final Map<String, String> queries);
}
