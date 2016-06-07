package com.capgemini.brokerageoffice.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the offer database table.
 * 
 */
@Entity
@NamedQuery(name="OfferEntity.findAll", query="SELECT o FROM OfferEntity o")
public class OfferEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String offertype;

	private String sharename;

	private BigDecimal shareprice;

	private int sharequantity;

	public OfferEntity() {
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