package com.codeofeverything.backendmarketstaredataingestion.repo;

import com.codeofeverything.backendmarketstaredataingestion.entity.SentimentAnalysisBlacklistVocabEntity;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SentimentAnalysisBlacklistVocabRepo extends MongoRepository<SentimentAnalysisBlacklistVocabEntity, String> {
  Optional<SentimentAnalysisBlacklistVocabEntity> findByWord(final String word);
}
