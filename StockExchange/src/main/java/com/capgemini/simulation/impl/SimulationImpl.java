package com.capgemini.simulation.impl;

import java.util.Calendar;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.bank.to.CurrencyWalletTo;
import com.capgemini.brokerageoffice.to.ShareWalletTo;
import com.capgemini.main.CustomCalendar;
import com.capgemini.player.Player;
import com.capgemini.simulation.Simulation;

@Service
public class SimulationImpl implements Simulation {

	private static final Logger logger = LogManager.getLogger(SimulationImpl.class);

	@Autowired
	Player player;

	Calendar calendar = Calendar.getInstance();

	@Override
	public void run(Date startDate, Date endDate) {
		showPlayerStatus();
		calendar.setTime(startDate);
		while (calendar.getTime().compareTo(endDate) <= 0) {
			logger.debug("Date: " + calendar.getTime().toString());
			updateCalendar(calendar.getTime());
			playOneTurn();
			calendar.add(Calendar.DATE, 1);
		}
		logger.debug("");
		showPlayerStatus();
	}

	private void playOneTurn() {
		player.playOneDayOnStock();
	}

	private void updateCalendar(Date date) {
		CustomCalendar.setCurrentDate(date);
	}

	private void showPlayerStatus() {
		logger.debug("Player shares");
		logger.debug("Share name" + "\t" + "Share quantity");
		if (player.showAllWalletShares() == null || player.showAllWalletShares().size() == 0) {
			logger.debug("Empty");
		} else {
			for (ShareWalletTo share : player.showAllWalletShares()) {
				logger.debug(share.getSharename() + "   " + share.getSharequantity());
			}
		}
		logger.debug("");
		logger.debug("Player funds");
		logger.debug("Currency name" + "\t" + "Currency amount");
		if (player.showAllWalletFunds() == null || player.showAllWalletFunds().size() == 0) {
			logger.debug("Empty");
		} else {
			for (CurrencyWalletTo currency : player.showAllWalletFunds()) {
				logger.debug(currency.getCurrencyname() + "\t" + currency.getCurrencyamount());
			}
		}
		logger.debug("");
	}
}
