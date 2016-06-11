package com.capgemini.bank.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.bank.entity.CurrencyWalletEntity;
import com.capgemini.bank.impl.CurrencyType;
import com.capgemini.bank.repository.CurrencyWalletRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CurrencyWalletRepositoryTest-context.xml")

public class CurrencyWalletRepositoryTest {

	@Autowired
	private CurrencyWalletRepository cwr;

	@Test
	//@Ignore
	public void testShouldFindCurrencyById() {
		// given
		CurrencyWalletEntity cw = cwr.findCurrencyById(1L);
		// when
		CurrencyType name = cw.getCurrencyname();
		// then
		assertEquals(CurrencyType.PLN, name);
	}
	
	@Test
	public void testShouldFindCurrencyByName(){
		// given
		CurrencyWalletEntity cw = cwr.findCurrencyByName(CurrencyType.PLN);
		// when
		CurrencyType name = cw.getCurrencyname();
		// then
		assertEquals(CurrencyType.PLN, name);
	}
	
	@Test
	public void testShouldFindAllCurrencies(){
		// given
		List<CurrencyWalletEntity> cw = cwr.findAll();
		// when
		boolean isNotEmpty = !cw.isEmpty();
		// then
		assertTrue(isNotEmpty);
	}
	
	@Test
	public void testShouldNotFindCurrencyWithWrongName(){
		// given
		CurrencyWalletEntity cw = cwr.findCurrencyByName(CurrencyType.EUR);
		// when
		
		// then
		assertNull(cw);
	}
	
	@Test
	public void testShouldNotFindCurrencyWithWrongId(){
		// given
		CurrencyWalletEntity cw = cwr.findCurrencyById(999L);
		// when
		
		// then
		assertNull(cw);
	}
	
	@Test
	public void testShouldUpdateCurrencyAmount(){
		// given
		CurrencyWalletEntity cw = cwr.findCurrencyByName(CurrencyType.PLN);
		cw.setCurrencyamount(200.00F);
		cwr.save(cw);
		// when
		float amount = cwr.findCurrencyByName(CurrencyType.PLN).getCurrencyamount();
		// then
		assertEquals(200.00F, amount, 0.0001);
		cw.setCurrencyamount(100.00F);
		cwr.save(cw);
	}
	
	@Test
	public void testShouldCreateCurrency(){
		// given
		CurrencyWalletEntity cw = cwr.save(new CurrencyWalletEntity(null, CurrencyType.EUR, 100.00F));
		// when
		CurrencyType name = cwr.findCurrencyByName(CurrencyType.EUR).getCurrencyname();
		// then
		assertEquals(CurrencyType.EUR, name);
		cwr.delete(cw);
	}

}
