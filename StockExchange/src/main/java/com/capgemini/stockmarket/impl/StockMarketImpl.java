package com.capgemini.stockmarket.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dataloader.Importer;
import com.capgemini.main.CustomCalendar;
import com.capgemini.stockmarket.StockMarket;
import com.capgemini.stockmarket.mapper.StockShareMapper;
import com.capgemini.stockmarket.repository.StockShareRepository;
import com.capgemini.stockmarket.to.StockShareTo;

@Service
public class StockMarketImpl implements StockMarket {

	private static final Logger logger = LogManager.getLogger(StockMarketImpl.class);

	@Autowired
	StockShareRepository stockShareRepository;

	@Override
	public List<StockShareTo> showAllCurrentStockShares() {
		return StockShareMapper.map2To(stockShareRepository.findStockShareByDate(CustomCalendar.getCurrentDate()));
	}

	@Override
	public List<StockShareTo> showAllHistoricalStockShares() {
		return StockShareMapper
				.map2To(stockShareRepository.findHistoryStockShareByDate(CustomCalendar.getCurrentDate()));
	}

	@Override
	public StockShareTo showCurrentStockShare(String shareName) {
		return StockShareMapper
				.map(stockShareRepository.findStockShareByNameAndDate(shareName, CustomCalendar.getCurrentDate()));
	}

	@PostConstruct
	private void initDB() {
		try {
			new Importer().importDataFromCsv();
		} catch (SQLException e) {
			logger.debug("Failed to initialize DB.");
			e.printStackTrace();
		}
	}

}
