package com.codeofeverything.backendmarketstaredataingestion.service.reddit;

import com.codeofeverything.backendmarketstaredataingestion.configuration.reddit.RedditConfigs;
import com.codeofeverything.backendmarketstaredataingestion.model.Submission;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubmissionUpsRule implements IEligibleSubmissionRule {

  private final RedditConfigs redditConfigs;

  @Autowired
  public SubmissionUpsRule(RedditConfigs redditConfigs) {
    this.redditConfigs = redditConfigs;
  }

  @Override
  public boolean validate(Submission submission) {
    final Integer ups = submission.getUps();
    if(ObjectUtils.isEmpty(ups))
      return false;

    return ups >= this.redditConfigs.getSubmissionUpsThreshold();
  }

  @Override
  public EDataCleanRule getEligibleValidatorName() {
    return EDataCleanRule.SUBMISSION_UPS;
  }
}
