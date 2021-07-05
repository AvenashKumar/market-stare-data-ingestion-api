package com.codeofeverything.backendmarketstaredataingestion.service.reddit.submission.comment;

import com.codeofeverything.backendmarketstaredataingestion.model.SubmissionComment;

public interface IEligibleSubmissionCommentRule {
  boolean validate(SubmissionComment submissionComment);
  ESubmissionCommentCleanRule getEligibleValidatorName();
}
