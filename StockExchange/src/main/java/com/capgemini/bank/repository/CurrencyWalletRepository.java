package com.capgemini.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.bank.entity.CurrencyWalletEntity;

@Transactional(readOnly = true)
public interface CurrencyWalletRepository extends JpaRepository<CurrencyWalletEntity, Long> {

	@Query("select cw from CurrencyWalletEntity cw where cw.id like :id")
	CurrencyWalletEntity findProjectById(@Param("id") Integer id);

}
