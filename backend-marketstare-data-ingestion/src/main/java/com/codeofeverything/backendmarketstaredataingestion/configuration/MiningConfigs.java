package com.codeofeverything.backendmarketstaredataingestion.configuration;

import com.codeofeverything.backendmarketstaredataingestion.service.rule.mining.ETickerMiningRule;
import com.codeofeverything.backendmarketstaredataingestion.service.rule.mining.ITickerMiningRule;
import com.codeofeverything.backendmarketstaredataingestion.service.rule.mining.TickerUpperCaseRule;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MiningConfigs {
  private final TickerUpperCaseRule tickerUpperCaseRule;

  @Autowired
  public MiningConfigs(
      TickerUpperCaseRule tickerUpperCaseRule) {
    this.tickerUpperCaseRule = tickerUpperCaseRule;
  }

  @Bean
  Map<ETickerMiningRule, ITickerMiningRule> tickerMiningRulesMap(){
    Map<ETickerMiningRule, ITickerMiningRule> mapTickerMiningRules = new LinkedHashMap<>();
    mapTickerMiningRules.put(ETickerMiningRule.TICKER_UPPER_CASE, tickerUpperCaseRule);
    return mapTickerMiningRules;
  }
}
