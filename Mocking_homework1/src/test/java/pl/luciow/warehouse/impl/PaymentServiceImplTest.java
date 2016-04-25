/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.luciow.warehouse.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import pl.luciow.warehouse.PaymentProcessor;
import pl.luciow.warehouse.model.Payment;
import pl.luciow.warehouse.util.PaymentValidator;

/**
 *
 * @author Mariusz
 */
@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceImplTest {

	private final static Logger logger = LogManager.getLogger(PaymentServiceImplTest.class);

	@InjectMocks
	private PaymentServiceImpl paymentServiceImpl = new PaymentServiceImpl();

	@Mock
	private PaymentProcessor paymentProcessor;

	@Mock
	private PaymentValidator paymentValidator;

	@Before
	public void prepareMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test()
	public void processPaymentTest() {
		//paymentProcessor = Mockito.mock(PaymentProcessor.class);
		//paymentValidator = Mockito.mock(PaymentValidator.class);
		Mockito.doAnswer(new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) {
				System.out.println("Entered");
				Object[] args = invocation.getArguments();
				List<String> errors = (List<String>) args[1];
				errors.add("error");
				args[1] = errors;
				return null;
			}
		}).when(paymentValidator).validate(Mockito.any(Payment.class), Mockito.anyListOf(String.class));

		List<String> test = new ArrayList<String>();
		paymentValidator.validate(new Payment(), test);

		try {
			paymentServiceImpl.processPayment(Mockito.any(Payment.class));
			logger.debug("Exception has not occured - multiple calls of processPayment method");
		} catch (Exception e) {
			Mockito.verify(paymentProcessor, Mockito.times(0)).processPayment(Mockito.any(Payment.class));
			logger.debug("Exception has occured - no calls of processPayment method");
		}
	}
}
