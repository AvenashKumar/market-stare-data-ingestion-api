package com.codeofeverything.backendmarketstaredataingestion.repo;

import com.codeofeverything.backendmarketstaredataingestion.entity.TickerCommentsEntity;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TickerCommentsRepo extends MongoRepository<TickerCommentsEntity, String> {
  Optional<TickerCommentsEntity> findByTicker(final String ticker);
}
