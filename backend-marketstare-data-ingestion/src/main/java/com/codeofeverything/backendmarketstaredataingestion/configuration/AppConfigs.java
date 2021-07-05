package com.codeofeverything.backendmarketstaredataingestion.configuration;

import com.codeofeverything.backendmarketstaredataingestion.entity.SentimentAnalysisBlacklistVocabEntity;
import com.codeofeverything.backendmarketstaredataingestion.entity.SentimentAnalysisHelperVocabEntity;
import com.codeofeverything.backendmarketstaredataingestion.repo.SentimentAnalysisBlacklistVocabRepo;
import com.codeofeverything.backendmarketstaredataingestion.repo.SentimentAnalysisHelperVocabRepo;
import com.codeofeverything.backendmarketstaredataingestion.service.rule.reddit.submission.ESubmissionCleanRule;
import com.codeofeverything.backendmarketstaredataingestion.service.rule.reddit.submission.IEligibleSubmissionRule;
import com.codeofeverything.backendmarketstaredataingestion.service.rule.reddit.submission.SubmissionCommentCleanRule;
import com.codeofeverything.backendmarketstaredataingestion.service.rule.reddit.submission.comment.ESubmissionCommentCleanRule;
import com.codeofeverything.backendmarketstaredataingestion.service.rule.reddit.submission.comment.IEligibleSubmissionCommentRule;
import com.codeofeverything.backendmarketstaredataingestion.service.rule.reddit.submission.comment.SubmissionCommentIgnoredAuthorRule;
import com.codeofeverything.backendmarketstaredataingestion.service.rule.reddit.submission.SubmissionFlairRule;
import com.codeofeverything.backendmarketstaredataingestion.service.rule.reddit.submission.SubmissionIgnoredAuthorRule;
import com.codeofeverything.backendmarketstaredataingestion.service.rule.reddit.submission.SubmissionUpsRule;
import com.codeofeverything.backendmarketstaredataingestion.service.rule.reddit.submission.SubmissionUpvoteRatioRule;
import com.codeofeverything.backendmarketstaredataingestion.service.rule.reddit.submission.comment.SubmissionCommentUpvoteRule;
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

  private final SubmissionIgnoredAuthorRule submissionIgnoredAuthorRule;

  private SubmissionCommentCleanRule submissionCommentCleanRule;

  private final SubmissionCommentIgnoredAuthorRule submissionCommentIgnoredAuthorRule;

  private final SubmissionCommentUpvoteRule submissionCommentUpvoteRule;

  @Autowired
  public AppConfigs(
      SentimentAnalysisHelperVocabRepo helperVocabRepo,
      SentimentAnalysisBlacklistVocabRepo blacklistVocabRepo,
      SubmissionUpvoteRatioRule submissionUpvoteRatioRule,
      SubmissionUpsRule submissionUpsRule,
      SubmissionFlairRule submissionFlairRule,
      SubmissionIgnoredAuthorRule submissionIgnoredAuthorRule,
      SubmissionCommentIgnoredAuthorRule submissionCommentIgnoredAuthorRule,
      SubmissionCommentUpvoteRule submissionCommentUpvoteRule) {
    this.helperVocabRepo = helperVocabRepo;
    this.blacklistVocabRepo = blacklistVocabRepo;
    this.submissionUpvoteRatioRule = submissionUpvoteRatioRule;
    this.submissionUpsRule = submissionUpsRule;
    this.submissionFlairRule = submissionFlairRule;
    this.submissionIgnoredAuthorRule = submissionIgnoredAuthorRule;
    this.submissionCommentIgnoredAuthorRule = submissionCommentIgnoredAuthorRule;
    this.submissionCommentUpvoteRule = submissionCommentUpvoteRule;
  }

  @Autowired
  public void setSubmissionCommentCleanRule(SubmissionCommentCleanRule submissionCommentCleanRule) {
    this.submissionCommentCleanRule = submissionCommentCleanRule;
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
  Map<ESubmissionCleanRule, IEligibleSubmissionRule> redditSubmissionRulesMap(){
    Map<ESubmissionCleanRule, IEligibleSubmissionRule> mapRedditRules=new HashMap<>();
    mapRedditRules.put(ESubmissionCleanRule.SUBMISSION_UPVOTE_RATIO, submissionUpvoteRatioRule);
    mapRedditRules.put(ESubmissionCleanRule.SUBMISSION_UPS, submissionUpsRule);
    mapRedditRules.put(ESubmissionCleanRule.SUBMISSION_FLAIR, submissionFlairRule);
    mapRedditRules.put(ESubmissionCleanRule.SUBMISSION_IGNORED_AUTHORS, submissionIgnoredAuthorRule);
    mapRedditRules.put(ESubmissionCleanRule.SUBMISSION_COMMENT_CLEAN, submissionCommentCleanRule);
    return mapRedditRules;
  }

  @Bean
  Map<ESubmissionCommentCleanRule, IEligibleSubmissionCommentRule> redditSubmissionCommentRulesMap(){
    Map<ESubmissionCommentCleanRule, IEligibleSubmissionCommentRule> mapRedditRules=new HashMap<>();
    mapRedditRules.put(ESubmissionCommentCleanRule.SUBMISSION_COMMENT_IGNORED_AUTHORS, submissionCommentIgnoredAuthorRule);
    mapRedditRules.put(ESubmissionCommentCleanRule.SUBMISSION_COMMENT_UPVOTE, submissionCommentUpvoteRule);
    return mapRedditRules;
  }
}
