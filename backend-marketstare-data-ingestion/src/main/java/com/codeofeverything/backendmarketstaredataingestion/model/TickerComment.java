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
public class TickerComment {
  private String ticker;
  private String author;
  private String comment;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TickerComment that = (TickerComment) o;
    return Objects.equals(ticker, that.ticker) && Objects
        .equals(author, that.author) && Objects.equals(comment, that.comment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ticker, author, comment);
  }
}
