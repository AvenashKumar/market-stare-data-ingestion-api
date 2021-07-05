package com.codeofeverything.backendmarketstaredataingestion.service.reddit;

import com.codeofeverything.backendmarketstaredataingestion.configuration.reddit.RedditConfigs;
import com.codeofeverything.backendmarketstaredataingestion.model.Submission;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubmissionIgnoredAuthorRule implements IEligibleSubmissionRule {

  private final RedditConfigs redditConfigs;

  @Autowired
  public SubmissionIgnoredAuthorRule(RedditConfigs redditConfigs) {
    this.redditConfigs = redditConfigs;
  }

  @Override
  public boolean validate(Submission submission) {
    final String author = submission.getAuthor();
    if(ObjectUtils.isEmpty(author))
      return false;

    return !redditConfigs.getSubmissionIgnoredAuthors().contains(author.toLowerCase());
  }

  @Override
  public EDataCleanRule getEligibleValidatorName() {
    return EDataCleanRule.SUBMISSION_IGNORED_AUTHORS;
  }
}
