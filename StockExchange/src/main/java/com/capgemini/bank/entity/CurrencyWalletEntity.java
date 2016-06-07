package com.capgemini.bank.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the currencywallet database table.
 * 
 */
@Entity
@NamedQuery(name="CurrencyWalletEntity.findAll", query="SELECT c FROM CurrencyWalletEntity c")
public class CurrencyWalletEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private BigDecimal currencyamount;

	private String currencyname;

	public CurrencyWalletEntity() {
	}
	
	public CurrencyWalletEntity(int id, String currencyname, BigDecimal currencyamount) {
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