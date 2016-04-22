/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.luciow.warehouse;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import pl.luciow.warehouse.impl.OrderServiceImpl;
import pl.luciow.warehouse.impl.WarehouseImpl;
import pl.luciow.warehouse.model.Mail;
import pl.luciow.warehouse.model.NotEnoughItemsException;
import pl.luciow.warehouse.model.Order;
import pl.luciow.warehouse.model.OrderProcessException;
import pl.luciow.warehouse.model.Payment;

/**
 *
 * @author Mariusz
 */
public class OrderServiceTest {

	private OrderService orderService;

	@SuppressWarnings("unchecked")
	@Test
	public void fillOrderSuccesTest() throws NotEnoughItemsException {
		Warehouse warehouseMock = Mockito.mock(Warehouse.class);
		orderService = new OrderServiceImpl(null, null, warehouseMock);
		Mockito.when(warehouseMock.removeItems(Mockito.any(List.class))).thenReturn(null);
		try {
			orderService.fillOrder(new Order());
		} catch (OrderProcessException e) {
			assertTrue(false);
		}
		assertTrue(true);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = OrderProcessException.class)
	public void fillOrderThrowTest() throws NotEnoughItemsException, OrderProcessException {
		Warehouse warehouseMock = Mockito.mock(Warehouse.class);
		orderService = new OrderServiceImpl(null, null, warehouseMock);
		Mockito.when(warehouseMock.removeItems(Mockito.any(List.class))).thenThrow(new NotEnoughItemsException());
		orderService.fillOrder(new Order());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void cancelOrderTest() {
		Warehouse warehouseMock = Mockito.mock(WarehouseImpl.class);
		Order orderMock = Mockito.mock(Order.class);
		orderService = new OrderServiceImpl(null, null, warehouseMock);
		Mockito.doCallRealMethod().when(warehouseMock).addItems(Mockito.any(List.class));
		try {
			orderService.cancelOrder(orderMock);
		} catch (OrderProcessException e) {
			assertTrue(false);
		}
		assertTrue(true);
	}

	@Test()
	public void processPaymentThrowTest() throws Exception {
		PaymentService paymentServices = Mockito.mock(PaymentService.class);
		Payment payment = Mockito.mock(Payment.class);
		MailService mailServiceMock = Mockito.mock(MailService.class);
		Mockito.when(paymentServices.processPayment(payment)).thenThrow(new Exception());
		orderService = new OrderServiceImpl(mailServiceMock, paymentServices, null);
		orderService.processPayment(new Order(), payment);
		Mockito.verify(mailServiceMock).sendMail((Mail) Mockito.argThat(new MyArgumentMatcher()));
	}

	@Test
	public void processPaymentSuccessTest() throws Exception {
		PaymentService paymentServices = Mockito.mock(PaymentService.class);
		Payment payment = Mockito.mock(Payment.class);
		MailService mailServiceMock = Mockito.mock(MailService.class);
		Mockito.when(paymentServices.processPayment(payment)).thenReturn(1L);
		orderService = new OrderServiceImpl(mailServiceMock, paymentServices, null);
		orderService.processPayment(new Order(), payment);
		Mockito.verify(mailServiceMock).sendMail((Mail) Mockito.argThat(new NewArgumentMatcher()));
	}

}
