package com.codeofeverything.backendmarketstaredataingestion.repo;

import com.codeofeverything.backendmarketstaredataingestion.entity.TickerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TickerRepo extends MongoRepository<TickerEntity, String> {

}
