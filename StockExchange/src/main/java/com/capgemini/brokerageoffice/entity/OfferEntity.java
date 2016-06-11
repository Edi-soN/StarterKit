package com.capgemini.brokerageoffice.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import com.capgemini.strategy.impl.ActionType;

/**
 * The persistent class for the offer database table.
 * 
 */
@Entity
@NamedQuery(name = "OfferEntity.findAll", query = "SELECT o FROM OfferEntity o")
public class OfferEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private ActionType offertype;

	private String sharename;

	private float shareprice;

	private int sharequantity;

	public OfferEntity() {
	}

	public OfferEntity(Long id, String sharename, int sharequantity, float shareprice, ActionType offertype) {
		this.id = id;
		this.offertype = offertype;
		this.sharename = sharename;
		this.shareprice = shareprice;
		this.sharequantity = sharequantity;
	}

	public OfferEntity(String sharename, int sharequantity, float shareprice, ActionType offertype) {
		this.offertype = offertype;
		this.sharename = sharename;
		this.shareprice = shareprice;
		this.sharequantity = sharequantity;
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