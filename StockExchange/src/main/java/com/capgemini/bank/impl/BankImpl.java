package com.capgemini.bank.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.bank.Bank;
import com.capgemini.bank.entity.CurrencyWalletEntity;
import com.capgemini.bank.mapper.CurrencyWalletMapper;
import com.capgemini.bank.repository.CurrencyWalletRepository;
import com.capgemini.bank.to.CurrencyWalletTo;
import com.capgemini.main.InvalidFundsException;
import com.capgemini.main.RandomNumberGenerator;
import com.capgemini.strategy.impl.ActionType;

@Service
@SuppressWarnings("unused")
public class BankImpl implements Bank {

	private static final Logger logger = LogManager.getLogger(BankImpl.class);

	private static final float LOWER_EXCHANGE_RATE = 3.9F;
	private static final float UPPER_EXCHANGE_RATE = 4.1F;
	private static final float EXCHANGE_MARGIN = 0.02F;

	@Autowired
	CurrencyWalletRepository currencyWalletRepository;

	@Override
	public List<CurrencyWalletTo> showPlayerFunds() {
		return CurrencyWalletMapper.map2To(currencyWalletRepository.findAll());
	}

	@Override
	public void exchangeCurrency(CurrencyType oldCurrency, CurrencyType newCurrency, float amount,
			ActionType offerType) {
		// TODO
	}

	@Override
	public TransactionConfirmation makeTransaction(CurrencyType currencyName, float amount, int bankAccountNumber) {
		TransactionConfirmation transactionConfirmation;
		try {
			validateCurrencyAmountAndTypeBeforeWithdraw(currencyName, amount);
			updateCurrencyAmount(currencyName, amount, ActionType.BUY);
			transactionConfirmation = new TransactionConfirmation(TransactionStatus.CONFIRMED);
		} catch (InvalidFundsException e) {
			logger.debug(e.getMessage());
			e.printStackTrace();
			transactionConfirmation = new TransactionConfirmation(TransactionStatus.FAILED);
		}
		return transactionConfirmation;
	}

	@Override
	public TransactionConfirmation depositFunds(CurrencyType currencyName, float amount) {
		TransactionConfirmation transactionConfirmation;
		try {
			validateCurrencyAmountAndTypeBeforeDeposit(currencyName, amount);
			updateCurrencyAmount(currencyName, amount, ActionType.SELL);
			transactionConfirmation = new TransactionConfirmation(TransactionStatus.CONFIRMED);
		} catch (InvalidFundsException e) {
			logger.debug(e.getMessage());
			e.printStackTrace();
			transactionConfirmation = new TransactionConfirmation(TransactionStatus.FAILED);
		}
		return transactionConfirmation;
	}

	@Override
	public TransactionConfirmation withdrawFunds(CurrencyType currencyName, float amount) {
		TransactionConfirmation transactionConfirmation;
		try {
			validateCurrencyAmountAndTypeBeforeWithdraw(currencyName, amount);
			updateCurrencyAmount(currencyName, amount, ActionType.BUY);
			transactionConfirmation = new TransactionConfirmation(TransactionStatus.CONFIRMED);
		} catch (InvalidFundsException e) {
			logger.debug(e.getMessage());
			e.printStackTrace();
			transactionConfirmation = new TransactionConfirmation(TransactionStatus.FAILED);
		}
		return transactionConfirmation;
	}

	private void updateCurrencyAmount(CurrencyType currencyName, float amount, ActionType offerType) {
		CurrencyWalletEntity currencyWalletEntity = currencyWalletRepository.findCurrencyByName(currencyName);
		float currentAmount = currencyWalletEntity.getCurrencyamount();
		if (ActionType.BUY.equals(offerType)) {
			currentAmount -= amount;
		}
		if (ActionType.SELL.equals(offerType)) {
			currentAmount += amount;
		}
		currencyWalletEntity.setCurrencyamount(currentAmount);
		currencyWalletRepository.save(currencyWalletEntity);
	}

	private void validateCurrencyAmountAndTypeBeforeWithdraw(CurrencyType currencyName, float amount)
			throws InvalidFundsException {
		CurrencyWalletEntity currencyWalletEntity = currencyWalletRepository.findCurrencyByName(currencyName);
		if (currencyName == null || amount < 0 || currencyWalletEntity == null
				|| amount > currencyWalletEntity.getCurrencyamount()) {
			throw new InvalidFundsException();
		}
	}

	private void validateCurrencyAmountAndTypeBeforeDeposit(CurrencyType currencyName, float amount)
			throws InvalidFundsException {
		CurrencyWalletEntity currencyWalletEntity = currencyWalletRepository.findCurrencyByName(currencyName);
		if (currencyName == null || amount < 0 || currencyWalletEntity == null) {
			throw new InvalidFundsException();
		}
	}

	private float calculateExchangeRate() {
		return RandomNumberGenerator.randomize(LOWER_EXCHANGE_RATE, UPPER_EXCHANGE_RATE);
	}

}
