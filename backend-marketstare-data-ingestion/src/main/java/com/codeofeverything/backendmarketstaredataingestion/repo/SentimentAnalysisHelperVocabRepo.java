package com.codeofeverything.backendmarketstaredataingestion.repo;

import com.codeofeverything.backendmarketstaredataingestion.entity.SentimentAnalysisHelperVocabEntity;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SentimentAnalysisHelperVocabRepo extends MongoRepository<SentimentAnalysisHelperVocabEntity, String> {
  Optional<SentimentAnalysisHelperVocabEntity> findByWord(final String word);
}
