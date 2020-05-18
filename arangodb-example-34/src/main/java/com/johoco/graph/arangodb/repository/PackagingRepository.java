package com.johoco.graph.arangodb.repository;

import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import com.arangodb.springframework.core.ArangoOperations;
import com.johoco.graph.arangodb.model.GroupId;
import com.johoco.graph.arangodb.model.Packaging;

@Repository
public class PackagingRepository extends FredRepository {
	private PackagingGraphRepository graphRepo;

	public PackagingRepository(final ArangoOperations arangoOperations,final PackagingGraphRepository graphRepo) {
		super(arangoOperations);
		this.graphRepo = graphRepo;
	}
	
	public Packaging insert(final Packaging packaging) {
		
		 Example<Packaging> example = Example.of(packaging);
		
		 Optional<Packaging> optionalPackaging =  graphRepo.findOne(example);
		 
		 Packaging result = null;
		 
		 if(optionalPackaging.isPresent()) {
			result = optionalPackaging.get(); 
			System.out.println("Didn't save GroupId...already exists for id " + result.getId());
		 } else {
			 result = this.graphRepo.save(packaging);
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
