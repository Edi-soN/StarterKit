package com.capgemini.brokerageoffice.to;

import com.capgemini.strategy.impl.ActionType;

public class OfferTo {

	private Long id;
	private ActionType offertype;
	private String sharename;
	private float shareprice;
	private int sharequantity;

	public OfferTo() {
	}

	public OfferTo(Long id, String sharename, int sharequantity, float shareprice, ActionType offertype) {
		this.id = id;
		this.offertype = offertype;
		this.sharename = sharename;
		this.shareprice = shareprice;
		this.sharequantity = sharequantity;
	}

	public OfferTo(ActionType offertype) {
		this.offertype = offertype;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ActionType getOffertype() {
		return this.offertype;
	}

	public void setOffertype(ActionType offertype) {
		this.offertype = offertype;
	}

	public String getSharename() {
		return this.sharename;
	}

	public void setSharename(String sharename) {
		this.sharename = sharename;
	}

	public float getShareprice() {
		return this.shareprice;
	}

	public void setShareprice(float shareprice) {
		this.shareprice = shareprice;
	}

	public int getSharequantity() {
		return this.sharequantity;
	}

	public void setSharequantity(int sharequantity) {
		this.sharequantity = sharequantity;
	}

}