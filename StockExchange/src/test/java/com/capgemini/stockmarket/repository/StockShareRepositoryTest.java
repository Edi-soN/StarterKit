package com.capgemini.stockmarket.repository;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.stockmarket.entity.StockShareEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "StockShareRepositoryTest-context.xml")

public class StockShareRepositoryTest {

	private Calendar calendar = Calendar.getInstance();
	
	@Autowired
	private StockShareRepository stockShareRepository;
	
	@Test
	public void testShouldFindShareById(){
		// given
		StockShareEntity sse = stockShareRepository.findStockShareById(1L);
		// when
		String name = sse.getSharename();
		// then
		assertEquals("JSW", name);
	}
	
	@Test
	public void testShouldNotFindShareById(){
		// given
		StockShareEntity sse = stockShareRepository.findStockShareById(9999L);
		// when
		
		// then
		assertNull(sse);
	}
	
	@Test
	public void testShouldFindShareByName(){
		// given 
		List<StockShareEntity> sse = stockShareRepository.findStockShareByName("JSW");
		// when
		boolean isEmpty = sse.isEmpty();
		// then
		assertFalse(isEmpty);
	}
	
	@Test
	public void testShouldNotFindShareByName(){
		// given
		List<StockShareEntity> sse = stockShareRepository.findStockShareByName("fake");
		// when
		boolean isEmpty = sse.isEmpty();
		// then
		assertTrue(isEmpty);
	}
	
	@Test
	public void testShouldFindShareByDate(){
		// given
		calendar.set(2013, 0, 2);
		List<StockShareEntity> sse = stockShareRepository.findStockShareByDate(calendar.getTime());
		// when
		boolean isEmpty = sse.isEmpty();
		// then
		assertFalse(isEmpty);
	}
	
	@Test
	public void testShouldNotReturnShareByDate(){
		// given
		calendar.set(2099, 0, 2);
		List<StockShareEntity> sse = stockShareRepository.findStockShareByDate(calendar.getTime());
		// when
		boolean isEmpty = sse.isEmpty();
		// then
		assertTrue(isEmpty);
	}
	
	@Test
	public void testShouldReturnShareByNameAndDate(){
		// given
		calendar.set(2013, 0, 2);
		StockShareEntity sse = stockShareRepository.findStockShareByNameAndDate("JSW", calendar.getTime());
		// when
		
		// then
		assertNotNull(sse);
	}
	
	@Test
	public void testShouldNotReturnShareByNameAndDate(){
		// given
		calendar.set(2099, 0, 2);
		StockShareEntity sse = stockShareRepository.findStockShareByNameAndDate("fake", calendar.getTime());
		// when
		
		// then
		assertNull(sse);
	}
	
	@Test
	public void testShouldReturnAllShares(){
		// given
		List<StockShareEntity> sse = stockShareRepository.findAll();
		// when
		boolean isEmpty = (sse.size() > 100);
		// then
		assertTrue(isEmpty);
	}
	
	@Test
	public void testShouldReturnHistoryShareByDate(){
		// given
		calendar.set(2099, 3, 2);
		List<StockShareEntity> sse = stockShareRepository.findHistoryStockShareByDate(calendar.getTime());
		// when
		boolean isEmpty = sse.isEmpty();
		// then
		assertFalse(isEmpty);
	}
	
}
