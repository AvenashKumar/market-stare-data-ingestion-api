package com.codeofeverything.backendmarketstaredataingestion.service.reddit.submission;

import com.codeofeverything.backendmarketstaredataingestion.model.Submission;

public interface IEligibleSubmissionRule {
  boolean validate(Submission submission);
  ESubmissionCleanRule getEligibleValidatorName();
}
