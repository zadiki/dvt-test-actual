package com.hackerrank.stocktrade.controller;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/erase")
public class ResourcesController {

    @Autowired
    private TradeService tradeService;

    @DeleteMapping("")
    public ResponseEntity<?> deleteAllTrade(){
        tradeService.deleteAllTrade();
        return new  ResponseEntity<>(HttpStatus.OK);
    }
    
}
