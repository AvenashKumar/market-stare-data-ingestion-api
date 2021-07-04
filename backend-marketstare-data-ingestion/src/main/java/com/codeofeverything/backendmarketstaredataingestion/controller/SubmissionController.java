package com.codeofeverything.backendmarketstaredataingestion.controller;

import com.codeofeverything.backendmarketstaredataingestion.model.Submission;
import com.codeofeverything.backendmarketstaredataingestion.service.SubmissionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/reddit/submission")
public class SubmissionController {

  private final SubmissionService submissionService;

  @Autowired
  public SubmissionController(SubmissionService submissionService) {
    this.submissionService = submissionService;
  }

  @PostMapping("/save")
  public void saveSubmissions(@RequestBody List<Submission> submissions){
    this.submissionService.saveSubmissions(submissions);
  }
}
