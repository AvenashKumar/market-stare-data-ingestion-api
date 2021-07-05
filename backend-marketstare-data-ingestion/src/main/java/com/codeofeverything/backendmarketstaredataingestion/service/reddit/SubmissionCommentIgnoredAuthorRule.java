package com.codeofeverything.backendmarketstaredataingestion.service.reddit;

import com.codeofeverything.backendmarketstaredataingestion.configuration.reddit.RedditConfigs;
import com.codeofeverything.backendmarketstaredataingestion.model.Submission;
import com.codeofeverything.backendmarketstaredataingestion.model.SubmissionComment;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubmissionCommentIgnoredAuthorRule implements IEligibleSubmissionRule {

  private final RedditConfigs redditConfigs;

  @Autowired
  public SubmissionCommentIgnoredAuthorRule(RedditConfigs redditConfigs) {
    this.redditConfigs = redditConfigs;
  }

  @Override
  public boolean validate(Submission submission) {
    final List<SubmissionComment> submissionComments = submission.getComments();
    if(ObjectUtils.isEmpty(submissionComments))
      return false;

    submission.setComments(submissionComments.stream()
        .filter(submissionComment -> !redditConfigs
                .getSubmissionCommentIgnoredAuthors()
                .contains(submissionComment.getAuthor().toLowerCase()))
        .collect(Collectors.toList()));

    return true;
  }

  @Override
  public EDataCleanRule getEligibleValidatorName() {
    return EDataCleanRule.SUBMISSION_COMMENT_IGNORED_AUTHORS;
  }
}
