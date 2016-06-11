package com.capgemini.brokerageoffice;

import java.util.List;

import com.capgemini.bank.impl.TransactionConfirmation;
import com.capgemini.brokerageoffice.to.OfferTo;
import com.capgemini.brokerageoffice.to.ShareWalletTo;
import com.capgemini.strategy.impl.ActionType;

public interface BrokerageOffice {

	public List<ShareWalletTo> showPlayerShares();
	
	public OfferTo makeOffer(String shareName, int quantity, ActionType offerType);
	
	public TransactionConfirmation buyShareFromOffer(Long offerId, TransactionConfirmation transactionConfirmation);
	
	public TransactionConfirmation sellShareFromOffer(Long offerId);
	
}
