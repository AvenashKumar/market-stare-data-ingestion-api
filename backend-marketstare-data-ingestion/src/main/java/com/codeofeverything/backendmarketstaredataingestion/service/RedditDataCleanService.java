package com.codeofeverything.backendmarketstaredataingestion.service;

import com.codeofeverything.backendmarketstaredataingestion.model.Submission;
import com.codeofeverything.backendmarketstaredataingestion.service.reddit.EDataCleanRule;
import com.codeofeverything.backendmarketstaredataingestion.service.reddit.IEligibleSubmissionRule;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedditDataCleanService {

  private final Map<EDataCleanRule, IEligibleSubmissionRule> mapCleanRules;

  @Autowired
  public RedditDataCleanService(Map<EDataCleanRule, IEligibleSubmissionRule> mapCleanRules) {
    this.mapCleanRules = mapCleanRules;
  }

  public boolean isEligibleSubmission(final Submission submission){
    for(Map.Entry<EDataCleanRule, IEligibleSubmissionRule> entry: mapCleanRules.entrySet()){
      if(!entry.getValue().validate(submission))
        return false;
    }
    return true;
  }
}
