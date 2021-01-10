package app.service;

import app.trade.CreateTradeRequest;
import core.framework.web.exception.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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
        assertThatThrownBy(() -> service.validateCurrency(request))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("currencyFrom");
    }

    @Test
    void invalidCurrencyTo() {
        var request = new CreateTradeRequest();
        request.currencyFrom = "EUR";
        request.currencyTo = "AAA";
        assertThatThrownBy(() -> service.validateCurrency(request))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("currencyTo");
    }

    @Test
    void validCountryCode() {
        var request = new CreateTradeRequest();
        request.currencyFrom = "EUR";
        request.currencyTo = "GBP";
        request.originatingCountry = "FR";
        assertDoesNotThrow(() -> service.validateCountry(request));
    }

    @Test
    void invalidCountryCode() {
        var request = new CreateTradeRequest();
        request.currencyFrom = "EUR";
        request.currencyTo = "GBP";
        request.originatingCountry = "HongKong";
        assertThatThrownBy(() -> service.validateCountry(request))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("originatingCountry");
    }
}
