package com.capgemini.brokerageoffice.to;

public class ShareWalletTo {

	private Long id;
	private String sharename;
	private int sharequantity;

	public ShareWalletTo() {
	}
	
	public ShareWalletTo(Long id, String sharename, int sharequantity) {
		this.id = id;
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