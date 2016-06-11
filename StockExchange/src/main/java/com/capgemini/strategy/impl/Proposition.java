package com.capgemini.strategy.impl;

public class Proposition {

	private String shareName;
	private int quantity;
	private float price;
	private ActionType offerType;

	public Proposition(String shareName, int quantity, float price, ActionType offerType) {
		this.shareName = shareName;
		this.quantity = quantity;
		this.price = price;
		this.offerType = offerType;
	}

	public String getShareName() {
		return shareName;
	}

	public int getQuantity() {
		return quantity;
	}

	public float getPrice() {
		return price;
	}

	public ActionType getOfferType() {
		return offerType;
	}

}
