package com.codeofeverything.backendmarketstaredataingestion.service.rule.reddit.submission;

import com.codeofeverything.backendmarketstaredataingestion.configuration.reddit.RedditConfigs;
import com.codeofeverything.backendmarketstaredataingestion.model.Submission;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubmissionFlairRule implements IEligibleSubmissionRule {

  private final RedditConfigs redditConfigs;

  @Autowired
  public SubmissionFlairRule(RedditConfigs redditConfigs) {
    this.redditConfigs = redditConfigs;
  }

  @Override
  public boolean validate(Submission submission) {
    final String flair = submission.getFlair();
    if(ObjectUtils.isEmpty(flair))
      return true;

    return redditConfigs.getSubmissionFlairs().contains(flair.toLowerCase());
  }

  @Override
  public ESubmissionCleanRule getEligibleValidatorName() {
    return ESubmissionCleanRule.SUBMISSION_FLAIR;
  }
}
