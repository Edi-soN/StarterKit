package com.capgemini.brokerageoffice.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.bank.Bank;
import com.capgemini.bank.impl.CurrencyType;
import com.capgemini.bank.impl.TransactionConfirmation;
import com.capgemini.bank.impl.TransactionStatus;
import com.capgemini.brokerageoffice.BrokerageOffice;
import com.capgemini.brokerageoffice.entity.OfferEntity;
import com.capgemini.brokerageoffice.entity.ShareWalletEntity;
import com.capgemini.brokerageoffice.mapper.OfferMapper;
import com.capgemini.brokerageoffice.mapper.ShareWalletMapper;
import com.capgemini.brokerageoffice.repository.OfferRepository;
import com.capgemini.brokerageoffice.repository.ShareWalletRepository;
import com.capgemini.brokerageoffice.to.OfferTo;
import com.capgemini.brokerageoffice.to.ShareWalletTo;
import com.capgemini.main.RandomNumberGenerator;
import com.capgemini.stockmarket.StockMarket;
import com.capgemini.stockmarket.to.StockShareTo;
import com.capgemini.strategy.impl.ActionType;

@Service
public class BrokerageOfficeImpl implements BrokerageOffice {

	private static final Logger logger = LogManager.getLogger(BrokerageOfficeImpl.class);

	private final float MINIMAL_SHARE_QUANTITY_PERCENTAGE = 0.8F;
	private final float MAXIMAL_SHARE_QUANTITY_PERCENTAGE = 1.0F;
	private final float MINIMAL_BUY_SHARE_PRICE_PERCENTAGE = 1.0F;
	private final float MAXIMAL_BUY_SHARE_PRICE_PERCENTAGE = 1.2F;
	private final float MINIMAL_SELL_SHARE_PRICE_PERCENTAGE = 0.98F;
	private final float MAXIMAL_SELL_SHARE_PRICE_PERCENTAGE = 1.0F;

	@Autowired
	private OfferRepository offferRepository;
	@Autowired
	private ShareWalletRepository shareWalletRepository;
	@Autowired
	private StockMarket stockMarket;
	@Autowired
	private Bank bank;

	@Override
	public List<ShareWalletTo> showPlayerShares() {
		return ShareWalletMapper.map2To(shareWalletRepository.findAll());
	}

	@Override
	public OfferTo makeOffer(String shareName, int quantity, ActionType offerType) {
		float sharePrice = calculateSharePrice(shareName, offerType);
		if (sharePrice == -1) {
			return new OfferTo(ActionType.SKIP);
		}
		int shareQuantity = calculateShareQuantity(quantity, offerType);
		return OfferMapper
				.map(offferRepository.save(new OfferEntity(null, shareName, shareQuantity, sharePrice, offerType)));
	}

	@Override
	public TransactionConfirmation buyShareFromOffer(Long offerId, TransactionConfirmation transactionConfirmation) {
		OfferEntity offer = offferRepository.findOfferById(offerId);
		if (transactionConfirmation == null
				|| !TransactionStatus.CONFIRMED.equals(transactionConfirmation.getConfirmation()) || offer == null
				|| ActionType.SELL.equals(offer.getOffertype())) {
			logger.debug("Operation canceled. Transaction confirmtation or offer issue.");
			return new TransactionConfirmation(TransactionStatus.FAILED);
		}
		ShareWalletEntity share = shareWalletRepository.findShareByName(offer.getSharename());
		if (share != null) {
			share.setSharequantity(share.getSharequantity() + offer.getSharequantity());
			shareWalletRepository.save(share);
			return new TransactionConfirmation(TransactionStatus.CONFIRMED);
		}
		shareWalletRepository.save(new ShareWalletEntity(null, offer.getSharename(), offer.getSharequantity()));
		return new TransactionConfirmation(TransactionStatus.CONFIRMED);
	}

	@Override
	public TransactionConfirmation sellShareFromOffer(Long offerId) {
		OfferEntity offer = offferRepository.findOfferById(offerId);
		ShareWalletEntity share = (null == offer ? null : shareWalletRepository.findShareByName(offer.getSharename()));
		if (offer == null || ActionType.BUY.equals(offer.getOffertype()) || share == null
				|| share.getSharequantity() < offer.getSharequantity()) {
			return new TransactionConfirmation(TransactionStatus.FAILED);
		}
		share.setSharequantity(share.getSharequantity() - offer.getSharequantity());
		shareWalletRepository.save(share);
		return bank.depositFunds(CurrencyType.PLN, offer.getShareprice() * offer.getSharequantity());
	}

	private float calculateSharePrice(String shareName, ActionType offerType) {
		float sharePriceToOffer = -1;
		if (!isShareAvailableOnStock(shareName)) {
			return sharePriceToOffer;
		}
		if (ActionType.BUY.equals(offerType)) {
			sharePriceToOffer = stockMarket.showCurrentStockShare(shareName).getShareprice() * RandomNumberGenerator
					.randomize(MINIMAL_BUY_SHARE_PRICE_PERCENTAGE, MAXIMAL_BUY_SHARE_PRICE_PERCENTAGE);
		}
		if (ActionType.SELL.equals(offerType)) {
			sharePriceToOffer = stockMarket.showCurrentStockShare(shareName).getShareprice() * RandomNumberGenerator
					.randomize(MINIMAL_SELL_SHARE_PRICE_PERCENTAGE, MAXIMAL_SELL_SHARE_PRICE_PERCENTAGE);
		}
		return sharePriceToOffer;
	}

	private int calculateShareQuantity(int quantity, ActionType offerType) {
		return Math.round(quantity * RandomNumberGenerator.randomize(MINIMAL_SHARE_QUANTITY_PERCENTAGE,
				MAXIMAL_SHARE_QUANTITY_PERCENTAGE));
	}

	private boolean isShareAvailableOnStock(String shareName) {
		StockShareTo share = stockMarket.showCurrentStockShare(shareName);
		return (share == null ? false : true);
	}

}
