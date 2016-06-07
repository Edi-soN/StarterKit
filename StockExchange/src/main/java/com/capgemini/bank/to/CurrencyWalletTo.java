package com.capgemini.bank.to;

import java.math.BigDecimal;


public class CurrencyWalletTo {

	private int id;
	private BigDecimal currencyamount;
	private String currencyname;

	public CurrencyWalletTo() {
	}
	
	public CurrencyWalletTo(int id, String currencyname, BigDecimal currencyamount) {
		this.id = id;
		this.currencyname = currencyname;
		this.currencyamount = currencyamount;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getCurrencyamount() {
		return this.currencyamount;
	}

	public void setCurrencyamount(BigDecimal currencyamount) {
		this.currencyamount = currencyamount;
	}

	public String getCurrencyname() {
		return this.currencyname;
	}

	public void setCurrencyname(String currencyname) {
		this.currencyname = currencyname;
	}

}