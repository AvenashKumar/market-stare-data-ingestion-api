package com.codeofeverything.backendmarketstaredataingestion.service.rule.mining;

public interface ITickerMiningRule {
  boolean validate(final String text);
  ETickerMiningRule getMiningRuleName();
}
