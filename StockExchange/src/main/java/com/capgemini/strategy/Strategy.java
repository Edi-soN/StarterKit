package com.capgemini.strategy;

import java.util.List;

import com.capgemini.bank.to.CurrencyWalletTo;
import com.capgemini.brokerageoffice.to.ShareWalletTo;
import com.capgemini.strategy.impl.Proposition;

public interface Strategy {

	public List<Proposition> proposeAction(List<CurrencyWalletTo> playerFunds, List<ShareWalletTo> playerShares);
	
}
