package com.hackerrank.stocktrade.controller;

import com.hackerrank.stocktrade.model.MaxMinResponse;
import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.service.TradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/trades")
public class TradesController {
    private static final Logger log = LoggerFactory.getLogger(TradesController.class);
    @Autowired
    private TradeService tradeService;

    @PostMapping("")
    public ResponseEntity<?> createTrade(@RequestBody(required = true) Trade trade) {
        tradeService.createAnewTrade(trade);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trade> getTradeById(@PathVariable Long id) {
        return new ResponseEntity<>(tradeService.findTradeById(id), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Trade>> getAllTrades() {
        return new ResponseEntity<>(tradeService.getAllTrades(), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Trade>> getTradesByUser(@PathVariable Long userId) {
        return new ResponseEntity<>(tradeService.findTradesByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/stocks/{stockSymbol}")
    public ResponseEntity<List<Trade>> findTradedFilteredBySymbolDateAndType(
            @PathVariable
            String stockSymbol,
            @RequestParam(name = "type", required = true)
            String tradeType,
            @RequestParam(required = true, name = "start")
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate startDate,
            @RequestParam(name = "end", required = true)
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate endDate
    ) {

        return new ResponseEntity<>(
                tradeService.findTradedFilteredBySymbolDateAndType(
                        stockSymbol,
                        tradeType,
                        Timestamp.valueOf(startDate.atStartOfDay()),
                        Timestamp.valueOf(LocalTime.MAX.atDate(endDate))),
                HttpStatus.OK);
    }


}
