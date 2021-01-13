package app.service;

import app.domain.Trade;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author ericchung
 */
public class Trades {
    final BlockingQueue<Trade> trades;
    private final int maxPollSize;

    public Trades(int maxTrade, int maxPollSize) {
        trades = new LinkedBlockingQueue<>(maxTrade);
        this.maxPollSize = maxPollSize;
    }

    public void add(Trade trade) {
        try {
            trades.put(trade);
        } catch (InterruptedException e) {
            throw new Error(e);
        }
    }

    public List<Trade> get() {
        List<Trade> currentTrades = new ArrayList<>();
        trades.drainTo(currentTrades, maxPollSize);
        return currentTrades;
    }
}
