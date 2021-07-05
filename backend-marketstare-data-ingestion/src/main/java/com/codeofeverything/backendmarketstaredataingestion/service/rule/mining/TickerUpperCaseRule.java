package com.codeofeverything.backendmarketstaredataingestion.service.rule.mining;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class TickerUpperCaseRule implements ITickerMiningRule{

  @Override
  public boolean validate(String text) {
    if(ObjectUtils.isEmpty(text))
      return false;

    return StringUtils.isAllUpperCase(text);
  }

  @Override
  public ETickerMiningRule getMiningRuleName() {
    return ETickerMiningRule.TICKER_UPPER_CASE;
  }
}
