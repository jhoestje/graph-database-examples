package com.johoco.graph.arangodb.repository;

import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import com.arangodb.springframework.core.ArangoOperations;
import com.johoco.graph.arangodb.model.relationship.PackagingOf;

@Repository
public class PackagingOfRepository extends FredRepository {
	private PackagingOfGraphRepository graphRepo;

	public PackagingOfRepository(final ArangoOperations arangoOperations,final PackagingOfGraphRepository graphRepo) {
		super(arangoOperations);
		this.graphRepo = graphRepo;
	}
	
	public PackagingOf insert(final PackagingOf packagingOf) {
		
		 Example<PackagingOf> example = Example.of(packagingOf);
		
		 Optional<PackagingOf> optionalPackagingOf =  graphRepo.findOne(example);
		 
		 PackagingOf result = null;
		 
		 if(optionalPackagingOf.isPresent()) {
			result = optionalPackagingOf.get(); 
			System.out.println("Didn't save GroupId...already exists for id " + result.getId());
		 } else {
			 result = this.graphRepo.save(packagingOf);
			 System.out.println("Saved new  GroupId... for id " + result.getId());
		 }
		 
		return result;
	}
	
	@Override
	@Resource(name = "groupId.arangodb.queries")
	protected void setMappedQueries(final Map<String, String> queries) {
		super.setQueries(queries);
	}

	public PackagingOf save(PackagingOf packagingOf) {
		return graphRepo.save(packagingOf);
	}

}
