package com.capgemini.brokerageoffice.to;

import java.math.BigDecimal;

public class OfferTo {

	private int id;
	private String offertype;
	private String sharename;
	private BigDecimal shareprice;
	private int sharequantity;

	public OfferTo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOffertype() {
		return this.offertype;
	}

	public void setOffertype(String offertype) {
		this.offertype = offertype;
	}

	public String getSharename() {
		return this.sharename;
	}

	public void setSharename(String sharename) {
		this.sharename = sharename;
	}

	public BigDecimal getShareprice() {
		return this.shareprice;
	}

	public void setShareprice(BigDecimal shareprice) {
		this.shareprice = shareprice;
	}

	public int getSharequantity() {
		return this.sharequantity;
	}

	public void setSharequantity(int sharequantity) {
		this.sharequantity = sharequantity;
	}

}