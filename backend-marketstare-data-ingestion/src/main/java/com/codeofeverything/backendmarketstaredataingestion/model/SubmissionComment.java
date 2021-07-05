package com.codeofeverything.backendmarketstaredataingestion.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SubmissionComment {
  private String author;
  private Integer score;
  private String body;
}
