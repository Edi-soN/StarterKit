package com.capgemini.main;

@SuppressWarnings("serial")
public class InvalidFundsException extends Exception {

	public InvalidFundsException() {
		super("Problem has occured while validating available funds. Please contact with bank helpdesk.");
	}
}
