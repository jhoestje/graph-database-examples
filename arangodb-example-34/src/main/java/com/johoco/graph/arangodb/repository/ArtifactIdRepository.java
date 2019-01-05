package com.johoco.graph.arangodb.repository;

import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import com.arangodb.springframework.core.ArangoOperations;
import com.johoco.graph.arangodb.model.ArtifactId;

@Repository
public class ArtifactIdRepository extends FredRepository {

	private ArtifactIdGraphRepository graphRepo;

	public ArtifactIdRepository(final ArangoOperations arangoOperations,final ArtifactIdGraphRepository graphRepo) {
		super(arangoOperations);
		this.graphRepo = graphRepo;
	}
	
	public ArtifactId insert(final ArtifactId artifactId) {
		
		 Example<ArtifactId> example = Example.of(artifactId);
		
		 Optional<ArtifactId> optionalArtifactId =  graphRepo.findOne(example);
		 
		 ArtifactId result = null;
		 
		 if(optionalArtifactId.isPresent()) {
			result = optionalArtifactId.get(); 
			System.out.println("Didn't save ArtifactId...already exists for id " + result.getId());
		 } else {
			 result = this.graphRepo.save(artifactId);
			 System.out.println("Saved new  ArtifactId... for id " + result.getId());
		 }
		 
		return result;
	}
	
	@Override
	@Resource(name = "artifactId.arangodb.queries")
	protected void setMappedQueries(final Map<String, String> queries) {
		super.setQueries(queries);
	}
}
