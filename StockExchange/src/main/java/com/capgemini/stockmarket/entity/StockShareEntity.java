package com.capgemini.stockmarket.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the stockshare database table.
 * 
 */
@Entity
@NamedQuery(name = "StockShareEntity.findAll", query = "SELECT s FROM StockShareEntity s")
public class StockShareEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
	private Date sharedate;

	private String sharename;

	private float shareprice;

	public StockShareEntity() {
	}

	public StockShareEntity(Long id, String sharename, float shareprice, Date sharedate) {
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