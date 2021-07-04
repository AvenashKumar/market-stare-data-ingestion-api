package com.codeofeverything.backendmarketstaredataingestion.repo;

import com.codeofeverything.backendmarketstaredataingestion.entity.SubmissionEntity;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubmissionRepo extends MongoRepository<SubmissionEntity, String> {
  Optional<SubmissionEntity> findByRedditSubmissionId(final String redditSubmissionId);
}
