package com.codeofeverything.backendmarketstaredataingestion.configuration;

import com.codeofeverything.backendmarketstaredataingestion.service.rule.mining.ETickerMiningRule;
import com.codeofeverything.backendmarketstaredataingestion.service.rule.mining.ITickerMiningRule;
import com.codeofeverything.backendmarketstaredataingestion.service.rule.mining.TickerAvailableListRule;
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
  private final TickerAvailableListRule tickerAvailableListRule;

  @Autowired
  public TickerMiningConfigs(
      TickerUpperCaseRule tickerUpperCaseRule,
      TickerMaxLengthRule tickerMaxLengthRule,
      TickerBlackListRule tickerBlackListRule,
      TickerAvailableListRule tickerAvailableListRule) {
    this.tickerUpperCaseRule = tickerUpperCaseRule;
    this.tickerMaxLengthRule = tickerMaxLengthRule;
    this.tickerBlackListRule = tickerBlackListRule;
    this.tickerAvailableListRule = tickerAvailableListRule;
  }


  @Bean
  Map<ETickerMiningRule, ITickerMiningRule> tickerMiningRulesMap(){
    Map<ETickerMiningRule, ITickerMiningRule> mapTickerMiningRules = new LinkedHashMap<>();
    mapTickerMiningRules.put(ETickerMiningRule.TICKER_UPPER_CASE, tickerUpperCaseRule);
    mapTickerMiningRules.put(ETickerMiningRule.TICKER_MAX_LENGTH, tickerMaxLengthRule);
    mapTickerMiningRules.put(ETickerMiningRule.TICKER_BLACK_LIST, tickerBlackListRule);
    mapTickerMiningRules.put(ETickerMiningRule.TICKER_AVAILABLE_LIST, tickerAvailableListRule);

    return mapTickerMiningRules;
  }
}
