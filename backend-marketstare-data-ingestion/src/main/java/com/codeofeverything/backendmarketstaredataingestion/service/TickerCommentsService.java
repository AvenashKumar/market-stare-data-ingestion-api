package com.codeofeverything.backendmarketstaredataingestion.service;

import com.codeofeverything.backendmarketstaredataingestion.entity.SubmissionEntity;
import com.codeofeverything.backendmarketstaredataingestion.entity.TickerCommentsEntity;
import com.codeofeverything.backendmarketstaredataingestion.model.SubmissionComment;
import com.codeofeverything.backendmarketstaredataingestion.model.TickerComment;
import com.codeofeverything.backendmarketstaredataingestion.repo.TickerCommentsRepo;
import com.codeofeverything.backendmarketstaredataingestion.service.rule.mining.ETickerMiningRule;
import com.codeofeverything.backendmarketstaredataingestion.service.rule.mining.ITickerMiningRule;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TickerCommentsService {

  private final Map<ETickerMiningRule, ITickerMiningRule> mapTickerMiningRules;

  private final TickerCommentsRepo tickerCommentsRepo;

  @Autowired
  public TickerCommentsService(
      Map<ETickerMiningRule, ITickerMiningRule> mapTickerMiningRules,
      TickerCommentsRepo tickerCommentsRepo) {
    this.mapTickerMiningRules = mapTickerMiningRules;
    this.tickerCommentsRepo = tickerCommentsRepo;
  }

  public void mineTickerCommentsFromSubmissions(List<SubmissionEntity> submissionEntities) {
    if (ObjectUtils.isEmpty(submissionEntities))
      return;

    final Map<String, Set<TickerComment>> mapAllTickerComments = new HashMap<>();
    submissionEntities.forEach(submissionEntity -> {
      final Map<String, Set<TickerComment>> mapTickerComments = mineTickerComments(
          submissionEntity.getComments());

      for (Map.Entry<String, Set<TickerComment>> entry : mapTickerComments.entrySet()) {
        if (mapAllTickerComments.containsKey(entry.getKey())) {
          mapAllTickerComments.get(entry.getKey()).addAll(entry.getValue());
        } else {
          mapAllTickerComments.put(entry.getKey(), entry.getValue());
        }
      }
    });

    final List<TickerCommentsEntity> tickerCommentsEntities = new ArrayList<>();
    mapAllTickerComments
        .forEach((ticker, tickerComments) -> tickerCommentsEntities.add(TickerCommentsEntity
            .builder()
            .ticker(ticker)
            .tickerComments(tickerComments)
            .build()));

    upsertTickerComments(tickerCommentsEntities);
  }

  private void upsertTickerComments(final List<TickerCommentsEntity> tickerCommentsEntities) {
    List<TickerCommentsEntity> updatedTickerEntities = new LinkedList<>();
    tickerCommentsEntities.forEach(tickerCommentsEntity -> {
      Optional<TickerCommentsEntity> optSavedTickerComment = tickerCommentsRepo
          .findByTicker(tickerCommentsEntity.getTicker());

      if (optSavedTickerComment.isPresent()) {
        TickerCommentsEntity savedEntity = optSavedTickerComment.get();
        savedEntity.getTickerComments().addAll(tickerCommentsEntity.getTickerComments());
        updatedTickerEntities.add(savedEntity);
      } else {
        updatedTickerEntities.add(tickerCommentsEntity);
      }
    });
    this.tickerCommentsRepo.saveAll(updatedTickerEntities);
  }

  private Map<String, Set<TickerComment>> mineTickerComments(
      List<SubmissionComment> submissionComments) {
    Map<String, Set<TickerComment>> mapTickerComments = new LinkedHashMap<>();
    submissionComments.forEach(submissionComment -> {
      final Optional<TickerComment> optionalTickerComment = mineTickerComment(submissionComment);
      if (optionalTickerComment.isPresent()) {
        final TickerComment tickerComment = optionalTickerComment.get();
        final String ticker = tickerComment.getTicker();
        if (mapTickerComments.containsKey(ticker)) {
          mapTickerComments.get(ticker).add(tickerComment);
        } else {
          Set<TickerComment> tickerComments = new LinkedHashSet<>();
          tickerComments.add(tickerComment);
          mapTickerComments.put(ticker, tickerComments);
        }
      }
    });
    return mapTickerComments;
  }

  private Optional<TickerComment> mineTickerComment(SubmissionComment submissionComment) {
    final String comment = submissionComment.getBody();
    if (ObjectUtils.isEmpty(comment))
      return Optional.empty();

    final String[] words = comment.split(" ");
    for (final String word : words) {
      final String updatedWord = applyPreProcessingRules(word);
      if (isTicker(updatedWord)) {
        return Optional.of(TickerComment.builder()
            .comment(comment)
            .ticker(updatedWord)
            .author(submissionComment.getAuthor())
            .build());
      }
    }
    return Optional.empty();
  }

  private String applyPreProcessingRules(final String word) {
    return replaceDollarSign(word);
  }

  private String replaceDollarSign(final String word) {
    return word.replace("$", "");
  }

  private boolean isTicker(final String word) {
    for (Map.Entry<ETickerMiningRule, ITickerMiningRule> entry : mapTickerMiningRules.entrySet()) {
      if (!entry.getValue().validate(word))
        return false;
    }
    return true;
  }
}
