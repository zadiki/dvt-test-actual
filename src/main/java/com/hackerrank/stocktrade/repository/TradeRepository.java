package com.hackerrank.stocktrade.repository;

import com.hackerrank.stocktrade.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {
    List<Trade> findAllByOrderByIdAsc();

    List<Trade> findAllByUserIdOrderByIdAsc(Long userId);

    List<Trade> findAllBySymbolAndTypeAndTimestampBetween(String symbol, String type, Timestamp startDate, Timestamp endDate);

    List<Trade> findAllBySymbolAndTimestampBetweenOrderByPriceDesc(String symbol, Timestamp startDate, Timestamp endDate);
    Optional<Trade> findTopByTimestampBetweenAndSymbolOrderByPriceDesc(Timestamp startDate,Timestamp endDate,String symbol);
}
