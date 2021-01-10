package app.service;

import app.trade.CreateTradeRequest;
import core.framework.web.exception.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author ericchung
 */
class TradeServiceTest {
    private TradeService service;

    @BeforeEach
    void createService() {
        service = new TradeService();
    }

    @Test
    void invalidCurrencyFrom() {
        var request = new CreateTradeRequest();
        request.currencyFrom = "AAA";
        assertThatThrownBy(() -> service.create(request))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("currencyFrom");
    }

    @Test
    void invalidCurrencyTo() {
        var request = new CreateTradeRequest();
        request.currencyFrom = "EUR";
        request.currencyTo = "AAA";
        assertThatThrownBy(() -> service.create(request))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("currencyTo");
    }
}
