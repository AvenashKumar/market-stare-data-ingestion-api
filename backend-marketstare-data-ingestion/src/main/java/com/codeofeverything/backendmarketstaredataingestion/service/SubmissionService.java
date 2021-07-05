package com.codeofeverything.backendmarketstaredataingestion.service;

import com.codeofeverything.backendmarketstaredataingestion.entity.SubmissionEntity;
import com.codeofeverything.backendmarketstaredataingestion.model.Submission;
import com.codeofeverything.backendmarketstaredataingestion.repo.SubmissionRepo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubmissionService {

  private final SubmissionRepo submissionRepo;

  private final RedditDataCleanService redditDataCleanService;

  @Autowired
  public SubmissionService(final SubmissionRepo submissionRepo,
      final RedditDataCleanService redditDataCleanService) {
    this.submissionRepo = submissionRepo;
    this.redditDataCleanService = redditDataCleanService;
  }

  private SubmissionEntity updateSubmissionEntity(final Submission submission, final Optional<SubmissionEntity> optSubmissionEntity){
    SubmissionEntity submissionEntity = optSubmissionEntity.orElse(new SubmissionEntity());
    submissionEntity.setRedditSubmissionId(submission.getRedditSubmissionId());
    submissionEntity.setAuthor(submission.getAuthor());
    submissionEntity.setComments(submission.getComments());
    submissionEntity.setCreatedUtc(submission.getCreatedUtc());
    submissionEntity.setFlair(submission.getFlair());
    submissionEntity.setSubreddit(submission.getSubreddit());
    submissionEntity.setUps(submission.getUps());
    submissionEntity.setUrl(submission.getUrl());
    submissionEntity.setTitle(submission.getTitle());
    submissionEntity.setUpvoteRatio(submission.getUpvoteRatio());
    return submissionEntity;
  }

  public void saveSubmissions(List<Submission> submissions) {
    List<SubmissionEntity> submissionEntities = new ArrayList<>();
    submissions.forEach(newSubmission -> {
      if(this.redditDataCleanService.isEligibleSubmission(newSubmission)) {
        final Optional<SubmissionEntity> optExistingSubmission = submissionRepo
            .findByRedditSubmissionId(newSubmission.getRedditSubmissionId());
        final SubmissionEntity submissionEntity = updateSubmissionEntity(newSubmission,
            optExistingSubmission);
        submissionEntities.add(submissionEntity);
      }
    });

    submissionRepo.saveAll(submissionEntities);
  }
}
