package app.service;

import app.domain.Trade;
import app.trade.CreateTradeRequest;
import app.trade.GetTradeResponse;
import core.framework.web.exception.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static core.framework.test.Assertions.assertBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * @author ericchung
 */
class TradeServiceTest {
    private TradeService service;

    @BeforeEach
    void createService() {
        service = new TradeService(1, 1);
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

    @Test
    void create() {
        var request = new CreateTradeRequest();
        request.currencyFrom = "EUR";
        request.currencyTo = "GBP";
        request.originatingCountry = "FR";

        assertDoesNotThrow(() -> service.create(request));
    }

    @Test
    void tradeResponseNotNull() {
        GetTradeResponse response = service.get();
        assertThat(response).isNotNull();
    }

    @Test
    void getAllTrades() {
        var request = new CreateTradeRequest();
        request.currencyFrom = "EUR";
        request.currencyTo = "GBP";
        request.originatingCountry = "FR";

        service.create(request);
        GetTradeResponse response = service.get();

        assertThat(response.trades).hasSize(1);
    }

    @Test
    void tradeViewNotNull() {
        Trade trade = trade();
        assertThat(service.view(trade)).isNotNull();
    }

    @Test
    void validTradeView() {
        Trade trade = trade();
        assertBean(service.view(trade)).isValid();
    }

    @Test
    void convertToTradeFromRequest() {
        var request = new CreateTradeRequest();
        request.userId = "123456";
        request.currencyFrom = "EUR";
        request.currencyTo = "GBP";
        request.amountSell = BigDecimal.valueOf(1000);
        request.amountBuy = BigDecimal.valueOf(747.1);
        request.rate = BigDecimal.valueOf(0.7471);
        request.timePlaced = "24-JAN-18 10:27:44";
        request.originatingCountry = "FR";

        Trade trade = service.trade(request);
        Trade expected = trade();

        assertThat(trade).usingRecursiveComparison().ignoringFields("id") // ignore random id
                         .isEqualTo(expected);
    }

    private Trade trade() {
        var trade = new Trade();
        trade.id = "id";
        trade.userId = "123456";
        trade.currencyFrom = "EUR";
        trade.currencyTo = "GBP";
        trade.amountSell = BigDecimal.valueOf(1000);
        trade.amountBuy = BigDecimal.valueOf(747.1);
        trade.rate = BigDecimal.valueOf(0.7471);
        trade.timePlaced = "24-JAN-18 10:27:44";
        trade.originatingCountry = "FR";
        return trade;
    }
}
