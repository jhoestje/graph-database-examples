package com.johoco.graph.arangodb.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.arangodb.ArangoCursor;
import com.arangodb.ArangoDBException;
import com.arangodb.springframework.core.ArangoOperations;
import com.arangodb.util.MapBuilder;
import com.johoco.graph.arangodb.model.ArtifactId;
import com.johoco.graph.arangodb.model.GroupId;
import com.johoco.graph.arangodb.model.relationship.ArtifactIdOf;

@Repository
public class ArtifactIdOfRepository extends FredRepository {
	private ArtifactIdOfGraphRepository graphRepo;

	public ArtifactIdOfRepository(final ArangoOperations arangoOperations, final ArtifactIdOfGraphRepository graphRepo) {
		super(arangoOperations);
		this.graphRepo = graphRepo;
	}

	public ArtifactIdOf insert(final ArtifactIdOf artifactIdOf) {

		// ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("artifactId",
		// GenericPropertyMatchers.exact())
		// .withMatcher("groupId", GenericPropertyMatchers.exact());
		// Example<ArtifactIdOf> example = Example.of(artifactIdOf, matcher);
		//
		// Optional<ArtifactIdOf> optionalArtifactIdOf =
		// this.graphRepo.findOne(example);
		//
		ArtifactIdOf result = null;

		Optional<ArtifactIdOf> optionalArtifactIdOf = this.findByKeys(artifactIdOf.getGroupId(),
				artifactIdOf.getArtifactId());

		if (optionalArtifactIdOf.isPresent()) {
			result = optionalArtifactIdOf.get();
			System.out.println("Didn't save ArtifactIdOf...already exists for id " + result.getId());
		} else {
			result = this.graphRepo.save(artifactIdOf);
			System.out.println("Saved new  ArtifactIdOf... for id " + result.getId());
		}

		return result;
	}

	public Optional<ArtifactIdOf> findByKeys(final GroupId groupId, final ArtifactId artifactId) {
		Optional<ArtifactIdOf> result = Optional.empty();
		try {
			final String query = getQuery("findByRelationshipKeys");
			Map<String, Object> bindVars = new MapBuilder().put("from", artifactId.getId()).put("to", groupId.getId())
					.get();

//			ArangoCursor<ArtifactIdOf> cursor = getDb().db("artifactDemo").query(query, bindVars, null,
//					ArtifactIdOf.class);
			ArangoCursor<ArtifactIdOf> cursor = getDb().query(query, bindVars, null,
					ArtifactIdOf.class);

			List<ArtifactIdOf> results = cursor.asListRemaining();
			if (results.size() == 1) {
				result = Optional.of(results.get(0));
			}
			// cursor.forEachRemaining(aDocument -> {
			// System.out.println("Key: " + aDocument.getKey());
			// });
		} catch (ArangoDBException e) {
			System.err.println("Failed to execute query. " + e.getMessage());
		}
		return result;
	}

	@Override
	@Resource(name = "artifactIdOf.arangodb.queries")
	protected void setMappedQueries(final Map<String, String> queries) {
		super.setQueries(queries);
	}
}
