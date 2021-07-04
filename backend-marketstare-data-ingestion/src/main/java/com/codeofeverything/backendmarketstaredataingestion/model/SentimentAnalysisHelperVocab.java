package com.codeofeverything.backendmarketstaredataingestion.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SentimentAnalysisHelperVocab {
  private String word;

  private double score;
}
