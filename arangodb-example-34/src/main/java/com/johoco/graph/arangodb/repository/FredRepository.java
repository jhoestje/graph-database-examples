package com.johoco.graph.arangodb.repository;

import java.util.Map;

import com.arangodb.springframework.core.ArangoOperations;

public abstract class FredRepository {
	private Map<String, String> queries;
	private ArangoOperations arangoOperations;
	
	public FredRepository(final ArangoOperations arangoOperations) {
		this.arangoOperations = arangoOperations;
	}

	protected void setQueries(final Map<String, String> queries) {
		this.queries = queries;
	}
	
	protected String getQuery(final String queryKey) {
		return queries.get(queryKey);
	}
	
	abstract void setMappedQueries(final Map<String, String> queries);
	
	protected ArangoOperations getDb() {
		return arangoOperations;
	}
}
