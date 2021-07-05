package com.codeofeverything.backendmarketstaredataingestion.service.reddit.submission.comment;

import com.codeofeverything.backendmarketstaredataingestion.configuration.reddit.RedditConfigs;
import com.codeofeverything.backendmarketstaredataingestion.model.SubmissionComment;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubmissionCommentUpvoteRule implements IEligibleSubmissionCommentRule {

  private final RedditConfigs redditConfigs;

  @Autowired
  public SubmissionCommentUpvoteRule(RedditConfigs redditConfigs) {
    this.redditConfigs = redditConfigs;
  }

  @Override
  public boolean validate(SubmissionComment submissionComment) {
    final Integer score = submissionComment.getScore();
    if(ObjectUtils.isEmpty(score))
      return false;

    return score >= redditConfigs.getSubmissionCommentUpvote();
  }

  @Override
  public ESubmissionCommentCleanRule getEligibleValidatorName() {
    return ESubmissionCommentCleanRule.SUBMISSION_COMMENT_UPVOTE;
  }
}
