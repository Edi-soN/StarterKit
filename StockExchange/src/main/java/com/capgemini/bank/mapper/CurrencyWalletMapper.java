package com.capgemini.bank.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.bank.entity.CurrencyWalletEntity;
import com.capgemini.bank.to.CurrencyWalletTo;

public class CurrencyWalletMapper {

	public static CurrencyWalletTo map(CurrencyWalletEntity currencyWalletEntity) {
		if (currencyWalletEntity != null) {
			return new CurrencyWalletTo(currencyWalletEntity.getId(), currencyWalletEntity.getCurrencyname(),
					currencyWalletEntity.getCurrencyamount());
		}
		return null;
	}

	public static CurrencyWalletEntity map(CurrencyWalletTo currencyWalletTo) {
		if (currencyWalletTo != null) {
			return new CurrencyWalletEntity(currencyWalletTo.getId(), currencyWalletTo.getCurrencyname(),
					currencyWalletTo.getCurrencyamount());
		}
		return null;
	}

	public static List<CurrencyWalletTo> map2To(List<CurrencyWalletEntity> currencyWalletEntities) {
		return currencyWalletEntities.stream().map(CurrencyWalletMapper::map).collect(Collectors.toList());
	}

	public static List<CurrencyWalletEntity> map2Entity(List<CurrencyWalletTo> currencyWalletTos) {
		return currencyWalletTos.stream().map(CurrencyWalletMapper::map).collect(Collectors.toList());
	}

}
