package com.johoco.graph.arangodb.repository;

import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import com.arangodb.springframework.core.ArangoOperations;
import com.johoco.graph.arangodb.model.relationship.PackagingOf;
import com.johoco.graph.arangodb.model.relationship.VersionOf;

@Repository
public class VersionOfRepository extends FredRepository {
	private VersionOfGraphRepository graphRepo;

	public VersionOfRepository(final ArangoOperations arangoOperations,final VersionOfGraphRepository graphRepo) {
		super(arangoOperations);
		this.graphRepo = graphRepo;
	}
	
	public VersionOf insert(final VersionOf versionOf) {
		
		 Example<VersionOf> example = Example.of(versionOf);
		
		 Optional<VersionOf> optionalVersionOf =  graphRepo.findOne(example);
		 
		 VersionOf result = null;
		 
		 if(optionalVersionOf.isPresent()) {
			result = optionalVersionOf.get(); 
			System.out.println("Didn't save GroupId...already exists for id " + result.getId());
		 } else {
			 result = this.graphRepo.save(versionOf);
			 System.out.println("Saved new  GroupId... for id " + result.getId());
		 }
		 
		return result;
	}
	
	@Override
	@Resource(name = "groupId.arangodb.queries")
	protected void setMappedQueries(final Map<String, String> queries) {
		super.setQueries(queries);
	}
}
