/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.luciow.warehouse.impl;

import java.util.List;

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
import pl.luciow.warehouse.util.ValidatorException;

/**
 *
 * @author Mariusz
 */
@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceImplTest {

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

	@Test(expected = ValidatorException.class)
	public void processPaymentTest() throws Exception {
		Mockito.doAnswer(new Answer<List<String>>(){
			public List<String> answer(InvocationOnMock invocation) {
				Object[] args = invocation.getArguments();
				//Object mock = invocation.getMock();
				args[1] = "error";
				return null;
		}}).when(paymentValidator).validate(new Payment(),Mockito.anyListOf(String.class));

		Mockito.verify(paymentServiceImpl, Mockito.times(1)).processPayment(new Payment());
		
//		Mockito.when(paymentValidator.validate(new Payment(), Mockito.anyListOf(String.class))).thenAnswer(new Answer<String>() {
//			public String answer(InvocationOnMock invocation) {
//				Object[] args = invocation.getArguments();
//				Object mock = invocation.getMock();
//				args[1] = "error";
//				return null;
//			}
//		});
	}
}
