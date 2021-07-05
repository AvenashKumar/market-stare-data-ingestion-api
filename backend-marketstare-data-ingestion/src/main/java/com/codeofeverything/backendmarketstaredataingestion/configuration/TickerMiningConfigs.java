package com.codeofeverything.backendmarketstaredataingestion.configuration;

import com.codeofeverything.backendmarketstaredataingestion.service.rule.mining.ETickerMiningRule;
import com.codeofeverything.backendmarketstaredataingestion.service.rule.mining.ITickerMiningRule;
import com.codeofeverything.backendmarketstaredataingestion.service.rule.mining.TickerBlackListRule;
import com.codeofeverything.backendmarketstaredataingestion.service.rule.mining.TickerMaxLengthRule;
import com.codeofeverything.backendmarketstaredataingestion.service.rule.mining.TickerUpperCaseRule;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TickerMiningConfigs {
  private final TickerUpperCaseRule tickerUpperCaseRule;
  private final TickerMaxLengthRule tickerMaxLengthRule;
  private final TickerBlackListRule tickerBlackListRule;

  @Autowired
  public TickerMiningConfigs(
      TickerUpperCaseRule tickerUpperCaseRule,
      TickerMaxLengthRule tickerMaxLengthRule,
      TickerBlackListRule tickerBlackListRule) {
    this.tickerUpperCaseRule = tickerUpperCaseRule;
    this.tickerMaxLengthRule = tickerMaxLengthRule;
    this.tickerBlackListRule = tickerBlackListRule;
  }

  @Bean
  Map<ETickerMiningRule, ITickerMiningRule> tickerMiningRulesMap(){
    Map<ETickerMiningRule, ITickerMiningRule> mapTickerMiningRules = new LinkedHashMap<>();
    mapTickerMiningRules.put(ETickerMiningRule.TICKER_UPPER_CASE, tickerUpperCaseRule);
    mapTickerMiningRules.put(ETickerMiningRule.TICKER_MAX_LENGTH, tickerMaxLengthRule);
    mapTickerMiningRules.put(ETickerMiningRule.TICKER_BLACK_LIST, tickerBlackListRule);

    return mapTickerMiningRules;
  }
}
