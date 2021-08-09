package com.codeofeverything.backendmarketstaredataingestion.controller;

import com.codeofeverything.backendmarketstaredataingestion.model.SentimentAnalysisBlacklistVocab;
import com.codeofeverything.backendmarketstaredataingestion.model.SentimentAnalysisHelperVocab;
import com.codeofeverything.backendmarketstaredataingestion.service.SentimentAnalysisBlacklistVocabService;
import com.codeofeverything.backendmarketstaredataingestion.service.SentimentAnalysisHelperVocabService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/sentiment/analysis")
public class SentimentAnalysisHelperVocabController {
  private final SentimentAnalysisHelperVocabService sentimentAnalysisHelperVocabService;
  private final SentimentAnalysisBlacklistVocabService sentimentAnalysisBlacklistVocabService;

  @Autowired
  public SentimentAnalysisHelperVocabController(
      SentimentAnalysisHelperVocabService sentimentAnalysisHelperVocabService,
      SentimentAnalysisBlacklistVocabService sentimentAnalysisBlacklistVocabService) {
    this.sentimentAnalysisHelperVocabService = sentimentAnalysisHelperVocabService;
    this.sentimentAnalysisBlacklistVocabService = sentimentAnalysisBlacklistVocabService;
  }

  @PostMapping("/helper/vocab/save")
  void saveSentimentAnalysisHelperVocab(@RequestBody final List<SentimentAnalysisHelperVocab> helperVocab){
    sentimentAnalysisHelperVocabService.save(helperVocab);
  }

  @PostMapping("/blacklist/vocab/save")
  void saveSentimentAnalysisBlacklistVocab(@RequestBody final List<SentimentAnalysisBlacklistVocab> blacklistVocab){
    sentimentAnalysisBlacklistVocabService.save(blacklistVocab);
  }
}
