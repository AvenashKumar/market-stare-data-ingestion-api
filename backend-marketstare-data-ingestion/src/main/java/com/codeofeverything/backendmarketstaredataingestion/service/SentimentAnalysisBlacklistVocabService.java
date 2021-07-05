package com.codeofeverything.backendmarketstaredataingestion.service;

import com.codeofeverything.backendmarketstaredataingestion.entity.SentimentAnalysisBlacklistVocabEntity;
import com.codeofeverything.backendmarketstaredataingestion.model.SentimentAnalysisBlacklistVocab;
import com.codeofeverything.backendmarketstaredataingestion.repo.SentimentAnalysisBlacklistVocabRepo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SentimentAnalysisBlacklistVocabService {

  private final SentimentAnalysisBlacklistVocabRepo sentimentAnalysisBlacklistVocabRepo;

  @Autowired
  public SentimentAnalysisBlacklistVocabService(
      SentimentAnalysisBlacklistVocabRepo sentimentAnalysisBlacklistVocabRepo) {
    this.sentimentAnalysisBlacklistVocabRepo = sentimentAnalysisBlacklistVocabRepo;
  }

  private SentimentAnalysisBlacklistVocabEntity updateSentimentAnalysisBlacklistVocabEntity(final SentimentAnalysisBlacklistVocab sentimentBlacklistVocab,
      final Optional<SentimentAnalysisBlacklistVocabEntity> optionalSentimentBlacklistVocabEntity){

    SentimentAnalysisBlacklistVocabEntity blacklistVocabEntity
        = optionalSentimentBlacklistVocabEntity.orElse(new SentimentAnalysisBlacklistVocabEntity());
    blacklistVocabEntity.setWord(sentimentBlacklistVocab.getWord().toUpperCase());
    return blacklistVocabEntity;
  }

  public void save(List<SentimentAnalysisBlacklistVocab> sentimentAnalysisBlacklistVocabs) {
    List<SentimentAnalysisBlacklistVocabEntity> blacklistVocabEntities = new ArrayList<>();
    sentimentAnalysisBlacklistVocabs.forEach(sentimentAnalysisBlacklistVocab -> {
      final Optional<SentimentAnalysisBlacklistVocabEntity> optExistingSubmission =
          sentimentAnalysisBlacklistVocabRepo.findByWord(sentimentAnalysisBlacklistVocab.getWord().toUpperCase());
      final SentimentAnalysisBlacklistVocabEntity blacklistVocabEntity = updateSentimentAnalysisBlacklistVocabEntity(sentimentAnalysisBlacklistVocab, optExistingSubmission);
      blacklistVocabEntities.add(blacklistVocabEntity);
    });

    sentimentAnalysisBlacklistVocabRepo.saveAll(blacklistVocabEntities);
  }
}
