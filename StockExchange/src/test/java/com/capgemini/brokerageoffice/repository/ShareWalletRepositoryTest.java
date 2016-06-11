package com.capgemini.brokerageoffice.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.brokerageoffice.entity.ShareWalletEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "ShareWalletRepositoryTest-context.xml")

public class ShareWalletRepositoryTest {

	@Autowired
	private ShareWalletRepository shareWalletRepository;
	
	@Test
	public void testShouldFindShareById(){
		// given
		ShareWalletEntity swe = shareWalletRepository.findShareById(1L);
		// when
		String name = swe.getSharename();
		// then
		assertEquals("JSW", name);
	}
	
	@Test
	public void testShouldNotFindShareById(){
		// given
		ShareWalletEntity swe = shareWalletRepository.findShareById(999L);
		// when
		
		// then
		assertNull(swe);
	}
	
	@Test
	public void testShouldReturnAllShares(){
		// given
		List<ShareWalletEntity> swe = shareWalletRepository.findAll();
		// when
		boolean isEmpty = swe.isEmpty();
		// then
		assertFalse(isEmpty);
	}
	
	@Test
	public void testShouldUpdateShare(){
		// given
		ShareWalletEntity swe = shareWalletRepository.findShareById(1L);
		swe.setSharequantity(200);
		shareWalletRepository.save(swe);
		// when
		int quantity = shareWalletRepository.findShareById(1L).getSharequantity();
		// then
		assertEquals(200, quantity);
		swe.setSharequantity(100);
		shareWalletRepository.save(swe);
	}
	
	@Test
	public void testShouldCreateShare(){
		// given
		ShareWalletEntity swe = shareWalletRepository.save(new ShareWalletEntity(null, "test", 100));
		// when
		String name = shareWalletRepository.findShareByName("test").getSharename();
		// then
		assertEquals("test", name);
		shareWalletRepository.delete(swe);
	}
	
}
