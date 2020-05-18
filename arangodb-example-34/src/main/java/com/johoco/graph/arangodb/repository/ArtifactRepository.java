package com.johoco.graph.arangodb.repository;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.arangodb.springframework.core.ArangoOperations;
import com.johoco.graph.arangodb.model.Artifact;
import com.johoco.graph.arangodb.model.ArtifactId;
import com.johoco.graph.arangodb.model.GroupId;
import com.johoco.graph.arangodb.model.Packaging;
import com.johoco.graph.arangodb.model.Version;
import com.johoco.graph.arangodb.model.relationship.ArtifactIdOf;
import com.johoco.graph.arangodb.model.relationship.PackagingOf;
import com.johoco.graph.arangodb.model.relationship.VersionOf;

@Repository
public class ArtifactRepository extends FredRepository {

	private ArtifactIdRepository artifactIdRepo;
	private GroupIdRepository groupIdRepo;
	private VersionGraphRepository versionRepo;
	private ClassifierGraphRepository classifierRepo;
	private PackagingGraphRepository packagingRepo;
	private PackagingOfRepository packagingOfRepo;
	private ArtifactGraphRepository artifactRepo;
	private ArtifactIdOfRepository artifactIdOfRepo;
	private VersionOfRepository versionOfRepo;

	public ArtifactRepository(final ArangoOperations arangoOperations, final ArtifactGraphRepository artifactRepo,
			final ArtifactIdRepository artifactIdRepo, final GroupIdRepository groupIdRepo,
			final VersionGraphRepository versionRepo, final ClassifierGraphRepository classifierRepo,
			final PackagingGraphRepository packagingRepo, final PackagingOfRepository packagingOfRepo,
			final ArtifactIdOfRepository artifactIdOfRepo, final VersionOfRepository versionOfRepo) {
		super(arangoOperations);
		this.groupIdRepo = groupIdRepo;
		this.artifactIdRepo = artifactIdRepo;
		this.versionRepo = versionRepo;
		this.classifierRepo = classifierRepo;
		this.packagingRepo = packagingRepo;
		this.artifactRepo = artifactRepo;
		this.artifactIdOfRepo = artifactIdOfRepo;
		this.versionOfRepo = versionOfRepo;
	}

	public Artifact save(final Artifact artifact) {
		GroupId gidResult = groupIdRepo.insert(artifact.getGroupId());
		ArtifactId aidResult = artifactIdRepo.insert(artifact.getArtifactId());
		ArtifactIdOf aidOfResult = artifactIdOfRepo.insert(new ArtifactIdOf(aidResult, gidResult));
		System.out.println("saved aidOf: " + aidOfResult.getId());

		Version vResult = versionRepo.save(artifact.getVersion());
		VersionOf vOfResult = versionOfRepo.insert(new VersionOf(vResult, aidResult));
		System.out.println("saved vOfResult: " + vOfResult.getId());

		Packaging pResult = packagingRepo.save(artifact.getPackaging());
		PackagingOf pOfResult = packagingOfRepo.save(new PackagingOf(pResult, vResult));
		System.out.println("saved pOfResult: " + pOfResult.getId());
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
