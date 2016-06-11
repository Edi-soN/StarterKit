package com.capgemini.player;

import java.util.List;

import com.capgemini.bank.to.CurrencyWalletTo;
import com.capgemini.brokerageoffice.to.ShareWalletTo;

public interface Player {

	public void playOneDayOnStock();
	
	public List<ShareWalletTo> showAllWalletShares();
	
	public List<CurrencyWalletTo> showAllWalletFunds();
	
}
