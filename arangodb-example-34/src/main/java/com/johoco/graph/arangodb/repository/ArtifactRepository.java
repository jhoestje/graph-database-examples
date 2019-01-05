package com.johoco.graph.arangodb.repository;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.arangodb.ArangoDB;
import com.arangodb.springframework.core.ArangoOperations;
import com.johoco.graph.arangodb.model.Artifact;
import com.johoco.graph.arangodb.model.ArtifactId;
import com.johoco.graph.arangodb.model.GroupId;
import com.johoco.graph.arangodb.model.relationship.ArtifactIdOf;

@Repository
public class ArtifactRepository extends FredRepository {

	private ArtifactIdRepository artifactIdRepo;
	private GroupIdRepository groupIdRepo;
	private VersionGraphRepository versionRepo;
	private ClassifierGraphRepository classifierRepo;
	private PackagingGraphRepository packagingRepo;
	private ArtifactGraphRepository artifactRepo;
	private ArtifactIdOfRepository artifactIdOfRepo;

	public ArtifactRepository(final ArangoOperations arangoOperations, final ArtifactGraphRepository artifactRepo,
			final ArtifactIdRepository artifactIdRepo, final GroupIdRepository groupIdRepo,
			final VersionGraphRepository versionRepo, final ClassifierGraphRepository classifierRepo,
			final PackagingGraphRepository packagingRepo, final ArtifactIdOfRepository artifactIdOfRepo) {
		super(arangoOperations);
		this.groupIdRepo = groupIdRepo;
		this.artifactIdRepo = artifactIdRepo;
		this.versionRepo = versionRepo;
		this.classifierRepo = classifierRepo;
		this.packagingRepo = packagingRepo;
		this.artifactRepo = artifactRepo;
		this.artifactIdOfRepo = artifactIdOfRepo;
	}

	public Artifact save(final Artifact artifact) {
		GroupId gidResult = groupIdRepo.insert(artifact.getGroupId());
		ArtifactId aidResult = artifactIdRepo.insert(artifact.getArtifactId());
		ArtifactIdOf aidOfResult = artifactIdOfRepo.insert(new ArtifactIdOf(aidResult, gidResult));
		System.out.println("saved aidOf: " + aidOfResult.getId());

		//
		// GroupId newone = new GroupId();
		// newone.setValue("value");
		// groupIdRepo.save(newone);
		// System.out.println("newone... " + newone.getId());

		// Iterable<ArtifactId> all = artifactIdRepo.findAll();
		// for(ArtifactId a : all) {
		// System.out.println("all...." + a.getId());
		// }

		// Iterable<ArtifactId> all2 = artifactIdRepo.findAll();

		// ArtifactId newone = new ArtifactId();
		// newone.setValue("art1");
		// artifactIdRepo.insert(newone);
		// System.out.println("newone artid....." + newone.getId());
		//
		// ArtifactIdOf aidOfResult = artifactIdOfRepo.save(new ArtifactIdOf(aidResult,
		// gidResult));
		// System.out.println("saved aidOf: " + aidOfResult.getId());
		//
		// versionRepo.save(artifact.getVersion());
		//
		// classifierRepo.save(artifact.getClassifier());
		// packagingRepo.save(artifact.getPackaging());
		// artifactRepo.save(artifact);
		return artifact;
	}

	@Override
	@Resource(name = "artifact.arangodb.queries")
	protected void setMappedQueries(final Map<String, String> queries) {
		super.setQueries(queries);
	}

}
