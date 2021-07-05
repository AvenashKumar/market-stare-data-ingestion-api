package com.codeofeverything.backendmarketstaredataingestion.service;

import com.codeofeverything.backendmarketstaredataingestion.model.Submission;
import com.codeofeverything.backendmarketstaredataingestion.service.reddit.submission.ESubmissionCleanRule;
import com.codeofeverything.backendmarketstaredataingestion.service.reddit.submission.IEligibleSubmissionRule;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedditDataCleanService {

  private final Map<ESubmissionCleanRule, IEligibleSubmissionRule> mapCleanRules;

  @Autowired
  public RedditDataCleanService(Map<ESubmissionCleanRule, IEligibleSubmissionRule> mapCleanRules) {
    this.mapCleanRules = mapCleanRules;
  }

  public boolean isEligibleSubmission(final Submission submission){
    for(Map.Entry<ESubmissionCleanRule, IEligibleSubmissionRule> entry: mapCleanRules.entrySet()){
      if(!entry.getValue().validate(submission))
        return false;
    }
    return true;
  }
}
