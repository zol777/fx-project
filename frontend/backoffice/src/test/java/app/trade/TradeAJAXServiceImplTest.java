package app.trade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author ericchung
 */
class TradeAJAXServiceImplTest {
    private TradeAJAXServiceImpl serviceImpl;

    @BeforeEach
    void createService() {
        serviceImpl = new TradeAJAXServiceImpl();
    }

    @Test
    void responseTradeNotNull() {
        assertThat(serviceImpl.trade(new TradeView())).isNotNull();
    }
}
