package com.hackerrank.stocktrade.controller;

import com.hackerrank.stocktrade.model.MaxMinResponse;
import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/stocks")
public class StocksController {

    @Autowired
    private TradeService tradeService;


    @GetMapping("/{stockSymbol}")
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
                        stockSymbol, tradeType,
                        Timestamp.valueOf(startDate.atStartOfDay()),
                        Timestamp.valueOf(LocalTime.MAX.atDate(endDate))),
                HttpStatus.OK);
    }
    @GetMapping("/{stockSymbol}/price")
    public ResponseEntity<MaxMinResponse> findMaxAndMinBySymbolAndDate(
            @PathVariable String stockSymbol,
            @RequestParam(required = true, name = "start")
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate startDate,
            @RequestParam(name = "end", required = true)
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate endDate
    ) {
        return new ResponseEntity<>(
                tradeService.findMaxAndMinBySymbolAndDate(
                        stockSymbol,  Timestamp.valueOf(startDate.atStartOfDay()),
                        Timestamp.valueOf(LocalTime.MAX.atDate(endDate))
                ), HttpStatus.OK);
    }
}
