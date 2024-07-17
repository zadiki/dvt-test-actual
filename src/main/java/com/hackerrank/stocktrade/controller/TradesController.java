package com.hackerrank.stocktrade.controller;

import com.hackerrank.stocktrade.model.MaxMinResponse;
import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/trades")
public class TradesController {
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

    @GetMapping("/users/{id}")
    public ResponseEntity<List<Trade>> getTradesByUser(@PathVariable Long userId) {
        return new ResponseEntity<>(tradeService.findTradesByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/stocks/{stocksSymbol}")
    public ResponseEntity<List<Trade>> findTradedFilteredBySymbolDateAndType(@PathVariable String stockSymbol, @RequestParam(name = "tradeType", required = true) String tradeType, @RequestParam(required = true, name = "startDate") Timestamp startDate, @RequestParam(name = "endDate", required = true) Timestamp endDate) {
        return new ResponseEntity<>(tradeService.findTradedFilteredBySymbolDateAndType(stockSymbol, tradeType, startDate, endDate), HttpStatus.OK);
    }
    @GetMapping("/stocks/{stocksSymbol}/price")
    public ResponseEntity<MaxMinResponse> findMaxAndMinBySymbolAndDate(@PathVariable String stockSymbol, @RequestParam(required = true, name = "startDate") Timestamp startDate, @RequestParam(name = "endDate", required = true) Timestamp endDate) {
        return new ResponseEntity<>(tradeService.findMaxAndMinBySymbolAndDate(stockSymbol,  startDate, endDate), HttpStatus.OK);
    }

}
