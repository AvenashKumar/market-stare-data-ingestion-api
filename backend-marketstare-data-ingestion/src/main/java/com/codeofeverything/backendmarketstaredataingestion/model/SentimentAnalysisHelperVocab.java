package com.codeofeverything.backendmarketstaredataingestion.model;

import java.util.Objects;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SentimentAnalysisHelperVocab that = (SentimentAnalysisHelperVocab) o;
    return word.equals(that.word);
  }

  @Override
  public int hashCode() {
    return Objects.hash(word);
  }
}
