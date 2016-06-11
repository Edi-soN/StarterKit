package com.capgemini.brokerageoffice.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.brokerageoffice.entity.OfferEntity;
import com.capgemini.strategy.impl.ActionType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "OfferRepositoryTest-context.xml")

public class OfferRepositoryTest {

	@Autowired
	private OfferRepository offerRepository;
	
	@Test
	public void testShouldFindShareById(){
		// given
		OfferEntity oe = offerRepository.findOfferById(1L);
		// when
		String name = oe.getSharename(); 
		// then
		assertEquals("JSW", name);
	}
	
	@Test
	public void testShouldNotFindShareById(){
		// given
		OfferEntity oe = offerRepository.findOfferById(999L);
		// when
		
		// then
		assertNull(oe);
	}
	
	@Test
	public void testShouldFindShareByOfferType(){
		// given
		List<OfferEntity> oe = offerRepository.findOfferByOfferType(ActionType.SELL);
		// when
		boolean isEmpty = oe.isEmpty();
		// then
		assertFalse(isEmpty);
	}
	
	@Test
	public void testShouldNotFindShareByOfferType(){
		// given
		List<OfferEntity> oe = offerRepository.findOfferByOfferType(ActionType.BUY);
		// when
		boolean isEmpty = oe.isEmpty();
		// then
		assertTrue(isEmpty);
	}
	
	@Test
	public void testShouldAddOffer(){
		// given
		OfferEntity oe = offerRepository.save(new OfferEntity(null, "Test", 100, 10.00F, ActionType.SELL));
		// when
		boolean isEmpty = offerRepository.findOfferByName("Test").isEmpty();
		// then
		assertFalse(isEmpty);
		offerRepository.delete(oe);
	}
	
}
