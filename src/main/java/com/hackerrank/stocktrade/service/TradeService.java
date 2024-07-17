package com.hackerrank.stocktrade.service;

import com.hackerrank.stocktrade.exception.DataNotFoundException;
import com.hackerrank.stocktrade.exception.DuplicateEntryException;
import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.User;
import com.hackerrank.stocktrade.repository.TradeRepository;
import com.hackerrank.stocktrade.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TradeService {
    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private UserRepository userRepository;

    public void createAnewTrade(Trade trade) {
        Optional<Trade> existing = tradeRepository.findById(trade.getId());
        if (existing.isPresent()) {
            throw new DuplicateEntryException("Trade already exist");
        }
        tradeRepository.save(trade);
    }

    public void deleteAllTrade() {
        tradeRepository.deleteAll();
    }

    public Trade findTradeById(Long id) {
        Optional<Trade> trade=tradeRepository.findById(id);
        if(!trade.isPresent()){
           throw new DataNotFoundException("Trade not found");
        }
        return trade.get();
    }

    public List<Trade> getAllTrades() {

        return tradeRepository.findAllByOrderByIdAsc();
    }

    public List<Trade> findTradesByUserId(Long userId) {
        Optional<User>  user=userRepository.findById(userId);
        if(user.isPresent()){
            throw new DataNotFoundException("user with that id does not exist");
        }
        List<Trade> tradeList= tradeRepository.findAllByOrderByIdAsc();
        if(tradeList.isEmpty()) throw new DataNotFoundException("User Does not have trades");
        return  tradeList;
    }
}
