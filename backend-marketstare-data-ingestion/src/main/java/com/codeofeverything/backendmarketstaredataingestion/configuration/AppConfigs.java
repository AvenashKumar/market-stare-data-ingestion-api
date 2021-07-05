package com.codeofeverything.backendmarketstaredataingestion.configuration;

import com.codeofeverything.backendmarketstaredataingestion.entity.SentimentAnalysisBlacklistVocabEntity;
import com.codeofeverything.backendmarketstaredataingestion.entity.SentimentAnalysisHelperVocabEntity;
import com.codeofeverything.backendmarketstaredataingestion.repo.SentimentAnalysisBlacklistVocabRepo;
import com.codeofeverything.backendmarketstaredataingestion.repo.SentimentAnalysisHelperVocabRepo;
import com.codeofeverything.backendmarketstaredataingestion.service.reddit.EDataCleanRule;
import com.codeofeverything.backendmarketstaredataingestion.service.reddit.IEligibleSubmissionRule;
import com.codeofeverything.backendmarketstaredataingestion.service.reddit.SubmissionFlairRule;
import com.codeofeverything.backendmarketstaredataingestion.service.reddit.SubmissionUpsRule;
import com.codeofeverything.backendmarketstaredataingestion.service.reddit.SubmissionUpvoteRatioRule;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfigs {
  private  final SentimentAnalysisHelperVocabRepo helperVocabRepo;

  private  final SentimentAnalysisBlacklistVocabRepo blacklistVocabRepo;

  private final SubmissionUpvoteRatioRule submissionUpvoteRatioRule;

  private final SubmissionUpsRule submissionUpsRule;

  private final SubmissionFlairRule submissionFlairRule;

  @Autowired
  public AppConfigs(
      SentimentAnalysisHelperVocabRepo helperVocabRepo,
      SentimentAnalysisBlacklistVocabRepo blacklistVocabRepo,
      SubmissionUpvoteRatioRule submissionUpvoteRatioRule,
      SubmissionUpsRule submissionUpsRule,
      SubmissionFlairRule submissionFlairRule) {
    this.helperVocabRepo = helperVocabRepo;
    this.blacklistVocabRepo = blacklistVocabRepo;
    this.submissionUpvoteRatioRule = submissionUpvoteRatioRule;
    this.submissionUpsRule = submissionUpsRule;
    this.submissionFlairRule = submissionFlairRule;
  }

  @Bean
  RestTemplate restTemplate(){
    return new RestTemplate();
  }

  @Bean
  ObjectMapper objectMapper(){
    return new ObjectMapper();
  }

  @Bean
  List<SentimentAnalysisHelperVocabEntity> findAllHelperVocabs(){
    return helperVocabRepo.findAll();
  }

  @Bean
  List<SentimentAnalysisBlacklistVocabEntity> findAllBlacklistVocabs(){
    return blacklistVocabRepo.findAll();
  }

  @Bean
  Map<EDataCleanRule, IEligibleSubmissionRule> redditRulesMap(){
    Map<EDataCleanRule, IEligibleSubmissionRule> mapRedditRules=new HashMap<>();
    mapRedditRules.put(EDataCleanRule.SUBMISSION_UPVOTE_RATIO, submissionUpvoteRatioRule);
    mapRedditRules.put(EDataCleanRule.SUBMISSION_UPS, submissionUpsRule);
    mapRedditRules.put(EDataCleanRule.SUBMISSION_FLAIR, submissionFlairRule);

    return mapRedditRules;
  }
}
