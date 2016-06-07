package com.capgemini.stockmarket.to;

import java.math.BigDecimal;
import java.util.Date;

public class StockshareTo {

	private int id;
	private Date sharedate;
	private String sharename;
	private BigDecimal shareprice;

	public StockshareTo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
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

	public BigDecimal getShareprice() {
		return this.shareprice;
	}

	public void setShareprice(BigDecimal shareprice) {
		this.shareprice = shareprice;
	}

}