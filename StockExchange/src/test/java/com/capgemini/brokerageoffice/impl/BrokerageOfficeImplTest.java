package com.capgemini.brokerageoffice.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
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

import com.capgemini.bank.Bank;
import com.capgemini.bank.impl.CurrencyType;
import com.capgemini.bank.impl.TransactionConfirmation;
import com.capgemini.bank.impl.TransactionStatus;
import com.capgemini.brokerageoffice.entity.OfferEntity;
import com.capgemini.brokerageoffice.entity.ShareWalletEntity;
import com.capgemini.brokerageoffice.repository.OfferRepository;
import com.capgemini.brokerageoffice.repository.ShareWalletRepository;
import com.capgemini.brokerageoffice.to.OfferTo;
import com.capgemini.brokerageoffice.to.ShareWalletTo;
import com.capgemini.stockmarket.StockMarket;
import com.capgemini.stockmarket.repository.StockShareRepository;
import com.capgemini.stockmarket.to.StockShareTo;
import com.capgemini.strategy.impl.ActionType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "BrokerageOfficeImplTest-context.xml")

public class BrokerageOfficeImplTest {

	@Mock
	private Bank bank;
	@Mock
	private StockMarket stockMarket;
	@Mock
	private StockShareRepository ssr;
	@Mock
	private ShareWalletRepository swr;
	@Mock
	private OfferRepository ofr;
	@InjectMocks
	private BrokerageOfficeImpl brokerageOfficeImpl = new BrokerageOfficeImpl();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testShouldFindAllPlayerShares() {
		// given
		@SuppressWarnings("serial")
		List<ShareWalletEntity> swe = new ArrayList<ShareWalletEntity>() {
			{
				add(new ShareWalletEntity(1L, "test1", 100));
				add(new ShareWalletEntity(2L, "test2", 200));
			}
		};
		Mockito.when(swr.findAll()).thenReturn(swe);
		// when
		List<ShareWalletTo> result = brokerageOfficeImpl.showPlayerShares();
		// then
		Mockito.verify(swr).findAll();
		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals("test1", result.get(0).getSharename());
	}

	@Test
	public void testShouldReturnOfferForBuyActionType() {
		// given
		StockShareTo sst = new StockShareTo(1L, "testShare", 1.0F, new Date());
		OfferEntity ofe = new OfferEntity(1L, "testShare", 1, 1.0F, ActionType.BUY);
		Mockito.when(stockMarket.showCurrentStockShare(Mockito.anyString())).thenReturn(sst);
		Mockito.when(ofr.save(Mockito.any(OfferEntity.class))).thenReturn(ofe);
		// when
		OfferTo offer = brokerageOfficeImpl.makeOffer("testShare", 1, ActionType.BUY);
		// then
		Mockito.verify(ofr).save(Mockito.any(OfferEntity.class));
		assertNotNull(offer);
		assertEquals("testShare", offer.getSharename());
		assertEquals(1, offer.getSharequantity());
		assertEquals(ActionType.BUY, offer.getOffertype());
	}

	@Test
	public void testShouldReturnOfferForSellActionType() {
		// given
		StockShareTo sst = new StockShareTo(1L, "testShare", 1.0F, new Date());
		OfferEntity ofe = new OfferEntity(1L, "testShare", 1, 1.0F, ActionType.SELL);
		Mockito.when(stockMarket.showCurrentStockShare(Mockito.anyString())).thenReturn(sst);
		Mockito.when(ofr.save(Mockito.any(OfferEntity.class))).thenReturn(ofe);
		// when
		OfferTo offer = brokerageOfficeImpl.makeOffer("testShare", 1, ActionType.SELL);
		// then
		Mockito.verify(ofr).save(Mockito.any(OfferEntity.class));
		assertNotNull(offer);
		assertEquals("testShare", offer.getSharename());
		assertEquals(1, offer.getSharequantity());
		assertEquals(ActionType.SELL, offer.getOffertype());
	}
	
	@Test
	public void testShouldReturnUpdatedBoughtShareFromOffer(){
		// given
		TransactionConfirmation transactionConfirmation1 = new TransactionConfirmation(TransactionStatus.CONFIRMED);
		OfferEntity offer = new OfferEntity(1L, "testShare", 100, 1.0F, ActionType.BUY);
		ShareWalletEntity share = new ShareWalletEntity(1L, "testShare", 100);
		Mockito.when(ofr.findOfferById(Mockito.anyLong())).thenReturn(offer);
		Mockito.when(swr.findShareByName(Mockito.anyString())).thenReturn(share);
		Mockito.when(swr.save(Mockito.any(ShareWalletEntity.class))).thenReturn(share);
		// when
		TransactionConfirmation transactionConfirmation = brokerageOfficeImpl.buyShareFromOffer(1L, transactionConfirmation1);
		// then
		Mockito.verify(ofr).findOfferById(Mockito.anyLong());
		Mockito.verify(swr).findShareByName(Mockito.anyString());
		Mockito.verify(swr, Mockito.times(1)).save(Mockito.any(ShareWalletEntity.class));
		assertNotNull(transactionConfirmation);
		assertEquals(TransactionStatus.CONFIRMED, transactionConfirmation.getConfirmation());
	}
	
	@Test
	public void testShouldReturnFailedBoughtShareFromOffer(){
		// given
		TransactionConfirmation transactionConfirmation1 = null;
		OfferEntity offer = new OfferEntity(1L, "testShare", 100, 1.0F, ActionType.BUY);
		Mockito.when(ofr.findOfferById(Mockito.anyLong())).thenReturn(offer);
		// when
		TransactionConfirmation transactionConfirmation = brokerageOfficeImpl.buyShareFromOffer(1L, transactionConfirmation1);
		// then
		Mockito.verify(ofr).findOfferById(Mockito.anyLong());
		assertEquals(TransactionStatus.FAILED, transactionConfirmation.getConfirmation());
	}
	
	@Test
	public void testShouldReturnNewBoughtShareFromOffer(){
		// given
		TransactionConfirmation transactionConfirmation1 = new TransactionConfirmation(TransactionStatus.CONFIRMED);
		OfferEntity offer = new OfferEntity(1L, "testShare", 100, 1.0F, ActionType.BUY);
		ShareWalletEntity share = new ShareWalletEntity(1L, "testShare", 100);
		Mockito.when(ofr.findOfferById(Mockito.anyLong())).thenReturn(offer);
		Mockito.when(swr.findShareByName(Mockito.anyString())).thenReturn(null);
		Mockito.when(swr.save(Mockito.any(ShareWalletEntity.class))).thenReturn(share);
		// when
		TransactionConfirmation transactionConfirmation = brokerageOfficeImpl.buyShareFromOffer(1L, transactionConfirmation1);
		// then
		Mockito.verify(ofr).findOfferById(Mockito.anyLong());
		Mockito.verify(swr).findShareByName(Mockito.anyString());
		Mockito.verify(swr, Mockito.times(1)).save(Mockito.any(ShareWalletEntity.class));
		assertNotNull(transactionConfirmation);
		assertEquals(TransactionStatus.CONFIRMED, transactionConfirmation.getConfirmation());
	}
	
	@Test
	public void testShouldReturnConfirmataionFromSoldShareOffer(){
		// given
		TransactionConfirmation transactionConfirmation = new TransactionConfirmation(TransactionStatus.CONFIRMED);
		OfferEntity offer = new OfferEntity(1L, "testShare", 100, 1.0F, ActionType.SELL);
		ShareWalletEntity share = new ShareWalletEntity(1L, "testShare", 100);
		Mockito.when(ofr.findOfferById(Mockito.anyLong())).thenReturn(offer);
		Mockito.when(swr.findShareByName(Mockito.anyString())).thenReturn(share);
		Mockito.when(swr.save(Mockito.any(ShareWalletEntity.class))).thenReturn(share);
		Mockito.when(bank.depositFunds(Mockito.any(CurrencyType.class), Mockito.anyFloat())).thenReturn(transactionConfirmation);
		// when
		TransactionConfirmation tc = brokerageOfficeImpl.sellShareFromOffer(1L);
		// then
		Mockito.verify(ofr).findOfferById(Mockito.anyLong());
		Mockito.verify(swr).findShareByName(Mockito.anyString());
		Mockito.verify(swr, Mockito.times(1)).save(Mockito.any(ShareWalletEntity.class));
		assertNotNull(tc);
		assertEquals(TransactionStatus.CONFIRMED, tc.getConfirmation());
	}
	
	@Test
	public void testShouldReturnFailedFromSoldShareOffer(){
		// given
		@SuppressWarnings("unused")
		TransactionConfirmation transactionConfirmation = new TransactionConfirmation(TransactionStatus.CONFIRMED);
		OfferEntity offer = null;
		ShareWalletEntity share = null;
		Mockito.when(ofr.findOfferById(Mockito.anyLong())).thenReturn(offer);
		Mockito.when(swr.findShareByName(Mockito.anyString())).thenReturn(share);
		// when
		TransactionConfirmation tc = brokerageOfficeImpl.sellShareFromOffer(1L);
		// then
		Mockito.verify(ofr).findOfferById(Mockito.anyLong());
		assertEquals(TransactionStatus.FAILED ,tc.getConfirmation());
	}

}
