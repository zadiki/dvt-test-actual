package com.hackerrank.stocktrade.model;

import java.sql.Timestamp;

public class MaxMinResponse {
    private String symbol;
    private Float highest,lowest;

    public MaxMinResponse(String symbol, Float highest, Float lowest) {
        this.symbol = symbol;
        this.highest = highest;
        this.lowest = lowest;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Float getHighest() {
        return highest;
    }

    public void setHighest(Float highest) {
        this.highest = highest;
    }

    public Float getLowest() {
        return lowest;
    }

    public void setLowest(Float lowest) {
        this.lowest = lowest;
    }
}
