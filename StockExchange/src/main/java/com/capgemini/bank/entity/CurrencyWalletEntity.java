package com.capgemini.bank.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import com.capgemini.bank.impl.CurrencyType;


/**
 * The persistent class for the currencywallet database table.
 * 
 */
@Entity
@NamedQuery(name="CurrencyWalletEntity.findAll", query="SELECT c FROM CurrencyWalletEntity c")
public class CurrencyWalletEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private float currencyamount;

	@Enumerated(EnumType.STRING)
	private CurrencyType currencyname;

	public CurrencyWalletEntity() {
	}
	
	public CurrencyWalletEntity(Long id, CurrencyType currencyname, float currencyamount) {
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