package com.codeofeverything.backendmarketstaredataingestion.service.rule.reddit.submission.comment;

import com.codeofeverything.backendmarketstaredataingestion.configuration.reddit.RedditConfigs;
import com.codeofeverything.backendmarketstaredataingestion.model.SubmissionComment;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubmissionCommentIgnoredAuthorRule implements IEligibleSubmissionCommentRule {

  private final RedditConfigs redditConfigs;

  @Autowired
  public SubmissionCommentIgnoredAuthorRule(RedditConfigs redditConfigs) {
    this.redditConfigs = redditConfigs;
  }

  @Override
  public boolean validate(SubmissionComment submissionComment) {
    final String author = submissionComment.getAuthor();
    if(ObjectUtils.isEmpty(author))
      return false;

    return !redditConfigs.getSubmissionCommentIgnoredAuthors().contains(author.toLowerCase());
  }

  @Override
  public ESubmissionCommentCleanRule getEligibleValidatorName() {
    return ESubmissionCommentCleanRule.SUBMISSION_COMMENT_IGNORED_AUTHORS;
  }
}
