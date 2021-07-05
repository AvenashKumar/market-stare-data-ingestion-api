package com.codeofeverything.backendmarketstaredataingestion.service.reddit;

import com.codeofeverything.backendmarketstaredataingestion.model.Submission;

public interface IEligibleSubmissionRule {
  boolean validate(Submission submission);
  EDataCleanRule getEligibleValidatorName();
}
