package app.service;

import app.domain.Trade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author ericchung
 */
class TradesTest {
    private Trades trades;

    @BeforeEach
    void create() {
        trades = new Trades(1, 1);
    }

    @Test
    void add() {
        trades.add(new Trade());
        assertThat(trades.trades).hasSize(1);
    }

    @Test
    void get() {
        trades.add(new Trade());

        assertThat(trades.get()).hasSize(1);
        assertThat(trades.trades).isEmpty();
    }
}
