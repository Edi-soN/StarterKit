package com.capgemini.stockmarket.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.main.CustomCalendar;
import com.capgemini.stockmarket.StockMarket;
import com.capgemini.stockmarket.mapper.StockShareMapper;
import com.capgemini.stockmarket.repository.StockShareRepository;
import com.capgemini.stockmarket.to.StockShareTo;

@Service
public class StockMarketImpl implements StockMarket {

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

}
