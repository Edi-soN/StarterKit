package com.capgemini.player.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.bank.Bank;
import com.capgemini.bank.impl.CurrencyType;
import com.capgemini.bank.to.CurrencyWalletTo;
import com.capgemini.brokerageoffice.BrokerageOffice;
import com.capgemini.brokerageoffice.to.OfferTo;
import com.capgemini.brokerageoffice.to.ShareWalletTo;
import com.capgemini.player.Player;
import com.capgemini.strategy.Strategy;
import com.capgemini.strategy.impl.ActionType;
import com.capgemini.strategy.impl.Proposition;

@Service
public class PlayerImpl implements Player {

	private static final Logger logger = LogManager.getLogger(PlayerImpl.class);

	@Autowired
	Strategy strategy;
	@Autowired
	Bank bank;
	@Autowired
	BrokerageOffice brokerageOffice;

	@Override
	public void playOneDayOnStock() {
		for (Proposition proposition : strategy.proposeAction(showAllWalletFunds(), showAllWalletShares())) {
			showPropositionInfo(proposition);
			playOneTurn(proposition);
		}
	}

	@Override
	public List<CurrencyWalletTo> showAllWalletFunds() {
		return bank.showPlayerFunds();
	}

	@Override
	public List<ShareWalletTo> showAllWalletShares() {
		return brokerageOffice.showPlayerShares();
	}

	private void playOneTurn(Proposition proposition) {
		OfferTo offerTo = brokerageOffice.makeOffer(proposition.getShareName(), proposition.getQuantity(),
				proposition.getOfferType());
		showOfferInfo(offerTo);
		if (proposition.getOfferType() == ActionType.BUY && offerTo.getShareprice() <= proposition.getPrice()) {
			brokerageOffice.buyShareFromOffer(offerTo.getId(),
					bank.makeTransaction(CurrencyType.PLN, proposition.getQuantity() * offerTo.getShareprice(), 1));
			logger.debug("Player bought share");
		}
		if (proposition.getOfferType() == ActionType.SELL && offerTo.getShareprice() >= proposition.getPrice()) {
			brokerageOffice.sellShareFromOffer(offerTo.getId());
			logger.debug("Player sold share");
		}
	}

	private void showPropositionInfo(Proposition proposition) {
		logger.debug("Strategy proposition:");
		logger.debug("Share name" + "\t" + "Share quantity" + "\t" + "Share price" + "\t" + "Offer type");
		logger.debug(proposition.getShareName() + "\t" + proposition.getQuantity() + "\t" + proposition.getPrice()
				+ "\t" + proposition.getOfferType().toString());
	}

	private void showOfferInfo(OfferTo offerTo) {
		logger.debug("Offer:");
		logger.debug("Offer name" + "\t" + "Offer quantity" + "\t" + "Offer price" + "\t" + "Offer type");
		logger.debug(offerTo.getSharename() + "\t" + offerTo.getSharequantity() + "\t" + offerTo.getShareprice() + "\t"
				+ offerTo.getOffertype().toString());
	}
}
