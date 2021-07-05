package com.codeofeverything.backendmarketstaredataingestion.service.rule.reddit.submission;

import com.codeofeverything.backendmarketstaredataingestion.model.Submission;
import com.codeofeverything.backendmarketstaredataingestion.model.SubmissionComment;
import com.codeofeverything.backendmarketstaredataingestion.service.rule.reddit.submission.comment.ESubmissionCommentCleanRule;
import com.codeofeverything.backendmarketstaredataingestion.service.rule.reddit.submission.comment.IEligibleSubmissionCommentRule;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubmissionCommentCleanRule implements IEligibleSubmissionRule {

  private final Map<ESubmissionCommentCleanRule, IEligibleSubmissionCommentRule> mapCommentCleanRules;

  @Autowired
  public SubmissionCommentCleanRule(Map<ESubmissionCommentCleanRule, IEligibleSubmissionCommentRule> mapCommentCleanRules) {
    this.mapCommentCleanRules = mapCommentCleanRules;
  }

  @Override
  public boolean validate(Submission submission) {
    final List<SubmissionComment> submissionComments = submission.getComments();
    if(ObjectUtils.isEmpty(submissionComments))
      return false;

    submission.setComments(submissionComments.stream().filter(this::isEligibleComment).collect(Collectors.toList()));

    return true;
  }

  private boolean isEligibleComment(final SubmissionComment submissionComment){
    for(Map.Entry<ESubmissionCommentCleanRule, IEligibleSubmissionCommentRule> entry: mapCommentCleanRules.entrySet()){
      if(!entry.getValue().validate(submissionComment))
        return false;
    }
    return true;
  }

  @Override
  public ESubmissionCleanRule getEligibleValidatorName() {
    return ESubmissionCleanRule.SUBMISSION_COMMENT_CLEAN;
  }
}
