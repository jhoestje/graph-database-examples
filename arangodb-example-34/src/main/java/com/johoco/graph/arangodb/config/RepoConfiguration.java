package com.johoco.graph.arangodb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import com.arangodb.ArangoDB;
import com.arangodb.springframework.core.ArangoOperations;

@Configuration
//@EnableArangoRepositories(basePackages = { "com.johoco.graph.arangodb.repository" })
//@ImportResource({ "classpath*:queries/queries.arango.xml" })
public class RepoConfiguration { // implements ArangoConfiguration {

	@Autowired
	private  ArangoOperations arangoOperations; 
	
@EventListener(ApplicationReadyEvent.class)
public void createDatabase() {
    System.out.println("hello world, I have just started up");
    ArangoDB arangoDB =  arangoOperations.driver();
    
    if (arangoDB == null) {
		arangoDB = new ArangoDB.Builder().build();
	}
	try {
		arangoDB.db(TEST_DB).drop();
	} catch (final ArangoDBException e) {
	}
	arangoDB.createDatabase(TEST_DB);
	BaseGraphTest.db = arangoDB.db(TEST_DB);

	final Collection<EdgeDefinition> edgeDefinitions = new ArrayList<EdgeDefinition>();
	final EdgeDefinition edgeDefinition = new EdgeDefinition().collection(EDGE_COLLECTION_NAME)
			.from(VERTEXT_COLLECTION_NAME).to(VERTEXT_COLLECTION_NAME);
	edgeDefinitions.add(edgeDefinition);
	try {
		db.createGraph(GRAPH_NAME, edgeDefinitions, null);
		addExampleElements();
	} catch (final ArangoDBException ex) {

	}
    
}

//	@Override
//	public Builder arango() {
//		System.out.println("arango()");
//		return new ArangoDB.Builder();//.host("localhost", 8529).user("root").password("secret");
//		// return new ArangoDB.Builder();
//		//.useProtocol(Protocol.HTTP_JSON)
//	}
//
//	@Override
//	public String database() {
//		System.out.println("database - name");
//		return "artifactDemo";
//	}
	
//	@PostConstruct
//	public void generateGraph() {
//		System.out.println("post - generateGraph");
//	}
}
