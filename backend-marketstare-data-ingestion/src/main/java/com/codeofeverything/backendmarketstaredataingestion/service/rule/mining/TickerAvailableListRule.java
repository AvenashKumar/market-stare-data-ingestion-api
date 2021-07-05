package com.codeofeverything.backendmarketstaredataingestion.service.rule.mining;

import java.util.Set;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TickerAvailableListRule implements ITickerMiningRule{

  private final Set<String> availableListVocab;

  @Autowired
  public TickerAvailableListRule(@Qualifier("findAllTickers") Set<String> availableListVocab) {
    this.availableListVocab = availableListVocab;
  }

  @Override
  public boolean validate(String text) {
    if(ObjectUtils.isEmpty(text))
      return false;

    return availableListVocab.contains(text);
  }

  @Override
  public ETickerMiningRule getMiningRuleName() {
    return ETickerMiningRule.TICKER_AVAILABLE_LIST;
  }
}
