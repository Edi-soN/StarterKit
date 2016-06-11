package com.capgemini.bank.impl;

public class TransactionConfirmation {

	private TransactionStatus confirmation;

	public TransactionStatus getConfirmation() {
		return confirmation;
	}
	
	public void setConfirmation(TransactionStatus confirmation) {
		this.confirmation = confirmation;
	}

	public TransactionConfirmation() {
		this.confirmation = TransactionStatus.CONFIRMED;
	}
	
	public TransactionConfirmation(TransactionStatus confirmation) {
		this.confirmation = confirmation;
	}

}
