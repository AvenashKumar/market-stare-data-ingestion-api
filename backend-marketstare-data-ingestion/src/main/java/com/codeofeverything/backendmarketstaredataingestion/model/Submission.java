package com.codeofeverything.backendmarketstaredataingestion.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.xml.stream.events.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Submission {
  @JsonProperty("submissionId")
  private String redditSubmissionId;
  private String url;
  private String subreddit;
  private String author;
  private String title;
  private String flair;
  @JsonProperty("upvote_ratio")
  private String upvoteRatio;
  private String ups;
  @JsonProperty("created_utc")
  private String createdUtc;
  private List<SubmissionComment> comments;
}
