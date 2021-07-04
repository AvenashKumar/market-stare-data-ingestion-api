package com.codeofeverything.backendmarketstaredataingestion.entity;

import com.codeofeverything.backendmarketstaredataingestion.model.SubmissionComment;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Submissions")
public class SubmissionEntity {
  @Id
  private String id;

  @Indexed(unique=true)
  private String redditSubmissionId;

  private String url;
  private String subreddit;
  private String author;
  private String title;
  private String flair;
  private String upvoteRatio;
  private String ups;
  private String createdUtc;

  private List<SubmissionComment> comments;

  @CreatedDate
  private Date createdDate;

  @LastModifiedDate
  private Date lastModifiedDate;
}
