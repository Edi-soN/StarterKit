package com.capgemini.strategy.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.bank.to.CurrencyWalletTo;
import com.capgemini.brokerageoffice.to.ShareWalletTo;
import com.capgemini.stockmarket.impl.StockMarketImpl;
import com.capgemini.stockmarket.to.StockShareTo;
import com.capgemini.strategy.Strategy;

@Service
public class StrategyImpl implements Strategy {

	@Autowired
	StockMarketImpl stockMarket;

	@Override
	public List<Proposition> proposeAction(List<CurrencyWalletTo> playerFunds, List<ShareWalletTo> playerShares) {
		return generatePropostionBasedOnStrategy(playerFunds, playerShares, StrategyType.SIMPLE);
	}

	private List<StockShareTo> retrieveAllCurrentStockShares() {
		return stockMarket.showAllCurrentStockShares();
	}

	private List<StockShareTo> retrueveAllHistoryStockShares() {
		return stockMarket.showAllHistoricalStockShares();
	}

	private List<Proposition> generatePropostionBasedOnStrategy(List<CurrencyWalletTo> playerFunds,
			List<ShareWalletTo> playerShares, StrategyType strategyType) {
		List<Proposition> propositions = new ArrayList<>();
		if (StrategyType.SIMPLE.equals(strategyType)) {
			propositions.add(new Proposition("JSW", 100, 95.00F, ActionType.BUY));
			propositions.add(new Proposition("JSW", 100, 95.00F, ActionType.SELL));
		}
		if (StrategyType.COMPLEX.equals(strategyType)) {
			// TODO
			retrieveAllCurrentStockShares();
			retrueveAllHistoryStockShares();
		}
		return propositions;
	}

}
