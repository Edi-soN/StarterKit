package com.capgemini.brokerageoffice.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the sharewallet database table.
 * 
 */
@Entity
@NamedQuery(name = "ShareWalletEntity.findAll", query = "SELECT s FROM ShareWalletEntity s")
public class ShareWalletEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String sharename;

	private int sharequantity;

	public ShareWalletEntity() {
	}

	public ShareWalletEntity(Long id, String sharename, int sharequantity) {
		this.id = id;
		this.sharename = sharename;
		this.sharequantity = sharequantity;
	}
	
	public ShareWalletEntity(String sharename, int sharequantity) {
		this.sharename = sharename;
		this.sharequantity = sharequantity;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSharename() {
		return this.sharename;
	}

	public void setSharename(String sharename) {
		this.sharename = sharename;
	}

	public int getSharequantity() {
		return this.sharequantity;
	}

	public void setSharequantity(int sharequantity) {
		this.sharequantity = sharequantity;
	}

}