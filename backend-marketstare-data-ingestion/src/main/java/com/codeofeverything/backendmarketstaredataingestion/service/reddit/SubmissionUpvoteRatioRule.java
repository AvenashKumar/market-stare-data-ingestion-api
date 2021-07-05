package com.codeofeverything.backendmarketstaredataingestion.service.reddit;

import com.codeofeverything.backendmarketstaredataingestion.configuration.reddit.RedditConfigs;
import com.codeofeverything.backendmarketstaredataingestion.model.Submission;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubmissionUpvoteRatioRule implements IEligibleSubmissionRule {

  private final RedditConfigs redditConfigs;

  @Autowired
  public SubmissionUpvoteRatioRule(RedditConfigs redditConfigs) {
    this.redditConfigs = redditConfigs;
  }

  @Override
  public boolean validate(Submission submission) {
    final Double upvoteRatio = submission.getUpvoteRatio();
    if(ObjectUtils.isEmpty(upvoteRatio))
      return false;

    return upvoteRatio >= this.redditConfigs.getSubmissionUpvoteRatioThreshold();
  }

  @Override
  public EDataCleanRule getEligibleValidatorName() {
    return EDataCleanRule.SUBMISSION_UPVOTE_RATIO;
  }
}
