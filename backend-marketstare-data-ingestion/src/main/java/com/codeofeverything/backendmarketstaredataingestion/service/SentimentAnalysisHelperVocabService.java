package com.codeofeverything.backendmarketstaredataingestion.service;

import com.codeofeverything.backendmarketstaredataingestion.entity.SentimentAnalysisHelperVocabEntity;
import com.codeofeverything.backendmarketstaredataingestion.model.SentimentAnalysisHelperVocab;
import com.codeofeverything.backendmarketstaredataingestion.repo.SentimentAnalysisHelperVocabRepo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SentimentAnalysisHelperVocabService {

  private final SentimentAnalysisHelperVocabRepo sentimentAnalysisHelperVocabRepo;

  @Autowired
  public SentimentAnalysisHelperVocabService(
      SentimentAnalysisHelperVocabRepo sentimentAnalysisHelperVocabRepo) {
    this.sentimentAnalysisHelperVocabRepo = sentimentAnalysisHelperVocabRepo;
  }

  private SentimentAnalysisHelperVocabEntity updateSentimentAnalysisHelperVocabEntity(final SentimentAnalysisHelperVocab sentimentHelperVocab,
      final Optional<SentimentAnalysisHelperVocabEntity> optionalSentimentHelperVocabEntity){

    SentimentAnalysisHelperVocabEntity helperVocabEntity
        = optionalSentimentHelperVocabEntity.orElse(new SentimentAnalysisHelperVocabEntity());
    helperVocabEntity.setScore(sentimentHelperVocab.getScore());
    helperVocabEntity.setWord(sentimentHelperVocab.getWord());
    return helperVocabEntity;
  }

  public void save(List<SentimentAnalysisHelperVocab> sentimentAnalysisHelperVocabs) {
    List<SentimentAnalysisHelperVocabEntity> helperVocabEntities = new ArrayList<>();
    sentimentAnalysisHelperVocabs.forEach(sentimentAnalysisHelperVocab -> {
      final Optional<SentimentAnalysisHelperVocabEntity> optExistingSubmission =
          sentimentAnalysisHelperVocabRepo.findByWord(sentimentAnalysisHelperVocab.getWord());
      final SentimentAnalysisHelperVocabEntity helperVocabEntity = updateSentimentAnalysisHelperVocabEntity(sentimentAnalysisHelperVocab, optExistingSubmission);
      helperVocabEntities.add(helperVocabEntity);
    });

    sentimentAnalysisHelperVocabRepo.saveAll(helperVocabEntities);
  }
}
