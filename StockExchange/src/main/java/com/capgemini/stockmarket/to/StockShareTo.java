package com.capgemini.stockmarket.to;

import java.math.BigDecimal;
import java.util.Date;

public class StockShareTo {

	private Long id;
	private Date sharedate;
	private String sharename;
	private float shareprice;

	public StockShareTo() {
	}
	
	public StockShareTo(Long id, String sharename, float shareprice, Date sharedate){
		this.id = id;
		this.sharename = sharename;
		this.shareprice = shareprice;
		this.sharedate = sharedate;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getSharedate() {
		return this.sharedate;
	}

	public void setSharedate(Date sharedate) {
		this.sharedate = sharedate;
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

}