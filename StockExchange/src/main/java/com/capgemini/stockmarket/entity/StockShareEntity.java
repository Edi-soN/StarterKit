package com.capgemini.stockmarket.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the stockshare database table.
 * 
 */
@Entity
@NamedQuery(name="StockShareEntity.findAll", query="SELECT s FROM StockShareEntity s")
public class StockShareEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.DATE)
	private Date sharedate;

	private String sharename;

	private BigDecimal shareprice;

	public StockShareEntity() {
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