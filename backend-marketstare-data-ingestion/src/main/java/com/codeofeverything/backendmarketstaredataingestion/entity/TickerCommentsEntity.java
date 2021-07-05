package com.codeofeverything.backendmarketstaredataingestion.entity;

import com.codeofeverything.backendmarketstaredataingestion.model.TickerComment;
import java.util.Date;
import java.util.Set;
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
@Document(collection = "TickerComments")
public class TickerCommentsEntity {
  @Id
  private String id;

  @Indexed(unique=true)
  private String ticker;

  private Set<TickerComment> tickerComments;

  @CreatedDate
  private Date createdDate;

  @LastModifiedDate
  private Date lastModifiedDate;
}
