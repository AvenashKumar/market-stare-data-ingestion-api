package com.codeofeverything.backendmarketstaredataingestion.configuration.reddit;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "data.clean.reddit")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RedditConfigs {
  private Double submissionUpvoteRatioThreshold;
  private Integer submissionUpsThreshold;
  private Set<String> submissionFlairs;
}
