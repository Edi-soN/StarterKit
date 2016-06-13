package com.capgemini.bank;

import java.util.List;

import com.capgemini.bank.impl.CurrencyType;
import com.capgemini.bank.impl.TransactionConfirmation;
import com.capgemini.bank.to.CurrencyWalletTo;
import com.capgemini.strategy.impl.ActionType;

public interface Bank {

	public List<CurrencyWalletTo> showPlayerFunds();

	public TransactionConfirmation exchangeCurrency(CurrencyType oldCurrency, CurrencyType newCurrency, float amount, ActionType offerType);
	
	public TransactionConfirmation makeTransaction(CurrencyType currencyName, float amount, int bankAccountNumber);
	
	public TransactionConfirmation depositFunds(CurrencyType currencyName, float amount);
	
	public TransactionConfirmation withdrawFunds(CurrencyType currencyName, float amount);
	
}
