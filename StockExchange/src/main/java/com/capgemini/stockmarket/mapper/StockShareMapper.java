package com.capgemini.stockmarket.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.stockmarket.entity.StockShareEntity;
import com.capgemini.stockmarket.to.StockShareTo;

public class StockShareMapper {

	public static StockShareTo map(StockShareEntity stockShareEntity) {
		if (stockShareEntity != null) {
			return new StockShareTo(stockShareEntity.getId(), stockShareEntity.getSharename(),
					stockShareEntity.getShareprice(), stockShareEntity.getSharedate());
		}
		return null;
	}

	public static StockShareEntity map(StockShareTo stockShareTo) {
		if (stockShareTo != null) {
			return new StockShareEntity(stockShareTo.getId(), stockShareTo.getSharename(), stockShareTo.getShareprice(),
					stockShareTo.getSharedate());
		}
		return null;
	}

	public static List<StockShareTo> map2To(List<StockShareEntity> stockShareEntities) {
		return stockShareEntities.stream().map(StockShareMapper::map).collect(Collectors.toList());
	}

	public static List<StockShareEntity> map2Entity(List<StockShareTo> stockShareTos) {
		return stockShareTos.stream().map(StockShareMapper::map).collect(Collectors.toList());
	}

}
