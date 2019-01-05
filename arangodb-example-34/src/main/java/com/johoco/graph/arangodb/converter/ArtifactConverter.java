package com.johoco.graph.arangodb.converter;

import com.johoco.graph.arangodb.model.Artifact;
import com.johoco.graph.arangodb.model.ArtifactId;
import com.johoco.graph.arangodb.model.Classifier;
import com.johoco.graph.arangodb.model.GroupId;
import com.johoco.graph.arangodb.model.Packaging;
import com.johoco.graph.arangodb.model.Version;
import com.johoco.graph.arangodb.model.api.ArtifactApiModel;

public class ArtifactConverter {

	public static Artifact convert(final ArtifactApiModel apiModel) {
		final Artifact model = new Artifact();

		final GroupId gid = new GroupId();
		gid.setValue(apiModel.getGroupId());

		final ArtifactId aid = new ArtifactId();
		aid.setValue(apiModel.getArtifactId());

		final Version v = new Version();
		v.setValue(apiModel.getVersion());

		final Classifier c = new Classifier();
		c.setValue(apiModel.getClassifier());

		final Packaging p = new Packaging();
		p.setValue(apiModel.getPackaging());

		model.setGroupId(gid);
		model.setArtifactId(aid);
		model.setVersion(v);
		model.setClassifier(c);
		model.setPackaging(p);

		return model;
	}

}
