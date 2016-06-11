package com.capgemini.bank.to;

import com.capgemini.bank.impl.CurrencyType;

public class CurrencyWalletTo {

	private Long id;
	private float currencyamount;
	private CurrencyType currencyname;

	public CurrencyWalletTo() {
	}
	
	public CurrencyWalletTo(Long id, CurrencyType currencyname, float currencyamount) {
		this.id = id;
		this.currencyname = currencyname;
		this.currencyamount = currencyamount;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getCurrencyamount() {
		return this.currencyamount;
	}

	public void setCurrencyamount(float currencyamount) {
		this.currencyamount = currencyamount;
	}

	public CurrencyType getCurrencyname() {
		return this.currencyname;
	}

	public void setCurrencyname(CurrencyType currencyname) {
		this.currencyname = currencyname;
	}

}