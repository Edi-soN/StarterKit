package com.capgemini.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.bank.entity.CurrencyWalletEntity;
import com.capgemini.bank.impl.CurrencyType;

@Repository
@Transactional(readOnly = true)
public interface CurrencyWalletRepository extends JpaRepository<CurrencyWalletEntity, Long> {

	@Query("select cw from CurrencyWalletEntity cw where cw.id = :id")
	CurrencyWalletEntity findCurrencyById(@Param("id") Long id);
	
	@Query("select cw from CurrencyWalletEntity cw where cw.currencyname like :name")
	CurrencyWalletEntity findCurrencyByName(@Param("name") CurrencyType name);

}
