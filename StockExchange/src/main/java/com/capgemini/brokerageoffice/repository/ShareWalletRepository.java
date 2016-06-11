package com.capgemini.brokerageoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.brokerageoffice.entity.ShareWalletEntity;

@Repository
@Transactional(readOnly = true)
public interface ShareWalletRepository extends JpaRepository<ShareWalletEntity, Long> {

	@Query("select sw from ShareWalletEntity sw where sw.id like :id")
	ShareWalletEntity findShareById(@Param("id") Long id);
	
	@Query("select sw from ShareWalletEntity sw where sw.sharename like :name")
	ShareWalletEntity findShareByName(@Param("name") String name);

}
