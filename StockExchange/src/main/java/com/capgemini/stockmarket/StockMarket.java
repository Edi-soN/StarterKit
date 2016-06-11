package com.capgemini.stockmarket;

import java.util.List;

import com.capgemini.stockmarket.to.StockShareTo;

public interface StockMarket {
	
	public List<StockShareTo> showAllCurrentStockShares();
	
	public List<StockShareTo> showAllHistoricalStockShares();
	
	public StockShareTo showCurrentStockShare(String shareName);
	
}
