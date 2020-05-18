package com.johoco.graph.arangodb.config;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import com.arangodb.ArangoDB;
import com.arangodb.ArangoDatabase;
import com.arangodb.ArangoEdgeCollection;
import com.arangodb.ArangoGraph;
import com.arangodb.entity.CollectionType;
import com.arangodb.entity.EdgeDefinition;
import com.arangodb.model.CollectionCreateOptions;
import com.arangodb.springframework.core.ArangoOperations;
import com.johoco.graph.arangodb.model.ArtifactId;
import com.johoco.graph.arangodb.model.Classifier;
import com.johoco.graph.arangodb.model.GroupId;
import com.johoco.graph.arangodb.model.Packaging;
import com.johoco.graph.arangodb.model.Version;
import com.johoco.graph.arangodb.model.relationship.ArtifactIdOf;
import com.johoco.graph.arangodb.model.relationship.PackagingOf;
import com.johoco.graph.arangodb.model.relationship.VersionOf;

@Configuration
//@EnableArangoRepositories(basePackages = { "com.johoco.graph.arangodb.repository" })
//@ImportResource({ "classpath*:queries/queries.arango.xml" })
public class RepoConfiguration { // implements ArangoConfiguration {

	@Autowired
	private ArangoOperations arangoOperations;

	// even though the arandodb document and edge annotations automatically create
	// the collections, we still need to create the graphs based on the collections
	@EventListener(ApplicationReadyEvent.class)
	public void createDatabase() {
		System.out.println("arango, I am ready");
		ArangoDB arangoDB = arangoOperations.driver();

		if (arangoDB == null) {
			arangoDB = new ArangoDB.Builder().build();
		}
//	try {
//		arangoDB.db(TEST_DB).drop();
//	} catch (final ArangoDBException e) {
//	}
		// check if the database needs to be created
		ArangoDatabase db = arangoDB.db("artifactDemo");
		if (db == null || !db.exists()) {
			arangoDB.createDatabase("artifactDemo");
			db = arangoDB.db("artifactDemo");
		}
//		// check which collections need to be added... may be new vertices
//		if (!db.collection(GroupId.getVertexName()).exists()) {
//			db.createCollection(GroupId.getVertexName());
//		}
//		if (!db.collection(ArtifactId.getVertexName()).exists()) {
//			db.createCollection(ArtifactId.getVertexName());
//		}
//		if (!db.collection(Version.getVertexName()).exists()) {
//			db.createCollection(Version.getVertexName());
//		}
//		if (!db.collection(Packaging.getVertexName()).exists()) {
//			db.createCollection(Packaging.getVertexName());
//		}
//		if (!db.collection(Classifier.getVertexName()).exists()) {
//			db.createCollection(Classifier.getVertexName());
//		}
//
//		// was there a way to do this by checking edge collection below?
//		if (!db.collection(ArtifactIdOf.getEdgeName()).exists()) {
//			db.createCollection(ArtifactIdOf.getEdgeName(), new CollectionCreateOptions().type(CollectionType.EDGES));
//		}
//		if (!db.collection(VersionOf.getEdgeName()).exists()) {
//			db.createCollection(VersionOf.getEdgeName(), new CollectionCreateOptions().type(CollectionType.EDGES));
//		}
//		if (!db.collection(PackageOf.getEdgeName()).exists()) {
//			db.createCollection(PackageOf.getEdgeName(), new CollectionCreateOptions().type(CollectionType.EDGES));
//		}

		// check if the graph needs to be created
		ArangoGraph graph = db.graph("artifacts");

		if (graph == null || !graph.exists()) {
			db.createGraph("artifacts", null, null);
			graph = db.graph("artifacts");
		}

		Collection<String> existingEdgeDefs = graph.getEdgeDefinitions();

		// check which edges need to be created; the edges and vertices will automatically be created if they don't exist
		if (!existingEdgeDefs.contains(ArtifactIdOf.getEdgeName())) {
			final EdgeDefinition artifactOfDef = new EdgeDefinition().collection(ArtifactIdOf.getEdgeName())
					.from(ArtifactId.getVertexName()).to(GroupId.getVertexName());
			graph.addEdgeDefinition(artifactOfDef);
		}

		if (!existingEdgeDefs.contains(VersionOf.getEdgeName())) {
			final EdgeDefinition versionOfDef = new EdgeDefinition().collection(VersionOf.getEdgeName())
					.from(Version.getVertexName()).to(ArtifactId.getVertexName());
			graph.addEdgeDefinition(versionOfDef);
		}

		if (!existingEdgeDefs.contains(PackagingOf.getEdgeName())) {
			final EdgeDefinition packageOfDef = new EdgeDefinition().collection(PackagingOf.getEdgeName())
					.from(Packaging.getVertexName()).to(Version.getVertexName());
			graph.addEdgeDefinition(packageOfDef);
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
