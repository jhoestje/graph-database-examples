package com.johoco.graph.arangodb.repository;

import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import com.arangodb.springframework.core.ArangoOperations;
import com.johoco.graph.arangodb.model.GroupId;

@Repository
public class GroupIdRepository extends FredRepository {
	private GroupIdGraphRepository graphRepo;

	public GroupIdRepository(final ArangoOperations arangoOperations,final GroupIdGraphRepository graphRepo) {
		super(arangoOperations);
		this.graphRepo = graphRepo;
	}
	
	public GroupId insert(final GroupId groupId) {
		
		 Example<GroupId> example = Example.of(groupId);
		
		 Optional<GroupId> optionalGroupId =  graphRepo.findOne(example);
		 
		 GroupId result = null;
		 
		 if(optionalGroupId.isPresent()) {
			result = optionalGroupId.get(); 
			System.out.println("Didn't save GroupId...already exists for id " + result.getId());
		 } else {
			 result = this.graphRepo.save(groupId);
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
