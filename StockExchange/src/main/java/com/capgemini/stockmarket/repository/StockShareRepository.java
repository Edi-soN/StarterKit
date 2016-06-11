package com.capgemini.stockmarket.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.stockmarket.entity.StockShareEntity;

@Repository
@Transactional(readOnly = true)
public interface StockShareRepository extends JpaRepository<StockShareEntity, Long> {

	@Query("select ss from StockShareEntity ss where ss.id = :id")
	StockShareEntity findStockShareById(@Param("id") Long id);

	@Query("select ss from StockShareEntity ss where ss.sharename like :name")
	List<StockShareEntity> findStockShareByName(@Param("name") String name);

	@Query("select ss from StockShareEntity ss where ss.sharedate = :date")
	List<StockShareEntity> findStockShareByDate(@Param("date") Date date);

	@Query("select ss from StockShareEntity ss where ss.sharename like :name and ss.sharedate = :date")
	StockShareEntity findStockShareByNameAndDate(@Param("name") String name, @Param("date") Date date);
	
	@Query("select ss from StockShareEntity ss where ss.sharedate <= :date")
	List<StockShareEntity> findHistoryStockShareByDate(@Param("date") Date date);

}
