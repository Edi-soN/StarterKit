package com.capgemini.brokerageoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.brokerageoffice.entity.OfferEntity;
import com.capgemini.strategy.impl.ActionType;

@Repository
@Transactional(readOnly = true)
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {

	@Query("select o from OfferEntity o where o.id like :id")
	OfferEntity findOfferById(@Param("id") Long id);
	
	@Query("select o from OfferEntity o where o.offertype like :offertype")
	List<OfferEntity> findOfferByOfferType(@Param("offertype") ActionType offertype);
	
	@Query("select o from OfferEntity o where o.sharename like :name")
	List<OfferEntity> findOfferByName(@Param("name") String name);

}
