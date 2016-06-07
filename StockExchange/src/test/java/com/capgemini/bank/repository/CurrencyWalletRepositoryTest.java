package com.capgemini.bank.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.bank.entity.CurrencyWalletEntity;
import com.capgemini.bank.repository.CurrencyWalletRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CurrencyWalletRepositoryTest-context.xml")

public class CurrencyWalletRepositoryTest {

	@Autowired
	private CurrencyWalletRepository cwr;

	@Test
	//@Ignore
	public void testShouldFindOfferById() {
		// given
		CurrencyWalletEntity cw = cwr.findProjectById(1);
		// when
		String name = cw.getCurrencyname();
		// then
		assertEquals("pln", name);
	}

}
