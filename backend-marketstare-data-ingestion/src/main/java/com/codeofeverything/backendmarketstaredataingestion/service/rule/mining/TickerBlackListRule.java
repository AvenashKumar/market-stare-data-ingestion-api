package com.codeofeverything.backendmarketstaredataingestion.service.rule.mining;

import java.util.Set;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TickerBlackListRule implements ITickerMiningRule{

  private final Set<String> blacklistVocab;

  @Autowired
  public TickerBlackListRule(@Qualifier("findAllBlacklistVocabs") Set<String> blacklistVocab) {
    this.blacklistVocab = blacklistVocab;
  }

  @Override
  public boolean validate(String text) {
    if(ObjectUtils.isEmpty(text))
      return false;

    return !blacklistVocab.contains(text);
  }

  @Override
  public ETickerMiningRule getMiningRuleName() {
    return ETickerMiningRule.TICKER_BLACK_LIST;
  }
}
