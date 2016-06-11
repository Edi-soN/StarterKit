package com.capgemini.bank.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.bank.entity.CurrencyWalletEntity;
import com.capgemini.bank.repository.CurrencyWalletRepository;
import com.capgemini.bank.to.CurrencyWalletTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "BankImplTest-context.xml")

public class BankImplTest {

	@Mock
	private CurrencyWalletRepository cwr;
	@InjectMocks
	private BankImpl bankImpl = new BankImpl();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testShouldFindAllCurrencies() {
		// given
		@SuppressWarnings("serial")
		List<CurrencyWalletEntity> cwe = new ArrayList<CurrencyWalletEntity>() {
			{
				add(new CurrencyWalletEntity(1L, CurrencyType.PLN, 100.00F));
				add(new CurrencyWalletEntity(2L, CurrencyType.PLN, 100.00F));
			}
		};
		Mockito.when(cwr.findAll()).thenReturn(cwe);
		// when
		List<CurrencyWalletTo> result = bankImpl.showPlayerFunds();
		// then
		Mockito.verify(cwr).findAll();
		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals(CurrencyType.PLN, result.get(0).getCurrencyname());
	}
	
	@Test
	public void testShouldReturnConfirmationWithConfirmedMessage(){
		// given
		Mockito.when(cwr.save(Mockito.any(CurrencyWalletEntity.class))).thenReturn(null);
		Mockito.when(cwr.findCurrencyByName(CurrencyType.PLN)).thenReturn(new CurrencyWalletEntity(1L, CurrencyType.PLN, 100.00F));
		// when
		TransactionConfirmation tc = bankImpl.makeTransaction(CurrencyType.PLN, 10.00F, 1);
		// then
		assertEquals(TransactionStatus.CONFIRMED, tc.getConfirmation());
	}
	
	@Test
	public void testShouldReturnConfirmationWithFailedMessageForInvalidCurrency(){
		// given
		
		// when
		TransactionConfirmation tc = bankImpl.makeTransaction(null, 10.00F, 1);
		// then
		assertEquals(TransactionStatus.FAILED, tc.getConfirmation());
	}
	
	@Test
	public void testShouldReturnConfirmationWithFailedMessageForNegativeCurrencyAmount(){
		// given
		
		// when
		TransactionConfirmation tc = bankImpl.makeTransaction(CurrencyType.PLN, -10.00F, 1);
		// then
		assertEquals(TransactionStatus.FAILED, tc.getConfirmation());
	}
	
	@Test
	public void testShouldReturnConfirmationWithFailedMessageForInvalidCurrencyAmount(){
		// given
		Mockito.when(cwr.findCurrencyByName(CurrencyType.PLN)).thenReturn(new CurrencyWalletEntity(1L, CurrencyType.PLN, 100.00F));
		// when
		TransactionConfirmation tc = bankImpl.makeTransaction(CurrencyType.PLN, 200.00F, 1);
		// then
		assertEquals(TransactionStatus.FAILED, tc.getConfirmation());
	}
	
	@Test
	public void testShouldReturnConfirmedStatusForDepositFunds(){
		// given
		Mockito.when(cwr.save(Mockito.any(CurrencyWalletEntity.class))).thenReturn(null);
		Mockito.when(cwr.findCurrencyByName(CurrencyType.PLN)).thenReturn(new CurrencyWalletEntity(1L, CurrencyType.PLN, 100.00F));
		// when
		TransactionConfirmation tc = bankImpl.depositFunds(CurrencyType.PLN, 10.00F);
		// then
		assertEquals(TransactionStatus.CONFIRMED, tc.getConfirmation());
	}

}
