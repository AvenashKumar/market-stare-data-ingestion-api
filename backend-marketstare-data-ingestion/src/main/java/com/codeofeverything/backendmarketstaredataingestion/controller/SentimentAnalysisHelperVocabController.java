package com.codeofeverything.backendmarketstaredataingestion.controller;

import com.codeofeverything.backendmarketstaredataingestion.model.SentimentAnalysisHelperVocab;
import com.codeofeverything.backendmarketstaredataingestion.service.SentimentAnalysisHelperVocabService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/sentiment/analysis/helper")
public class SentimentAnalysisHelperVocabController {
  private final SentimentAnalysisHelperVocabService sentimentAnalysisHelperVocabService;

  @Autowired
  public SentimentAnalysisHelperVocabController(SentimentAnalysisHelperVocabService sentimentAnalysisHelperVocabService) {
    this.sentimentAnalysisHelperVocabService = sentimentAnalysisHelperVocabService;
  }

  @PostMapping("/vocab/save")
  void saveSentimentAnalysisHelperVocab(@RequestBody final List<SentimentAnalysisHelperVocab> helperVocab){
    sentimentAnalysisHelperVocabService.save(helperVocab);
  }
}
