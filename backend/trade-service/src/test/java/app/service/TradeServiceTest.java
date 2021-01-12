package app.service;

import app.domain.Trade;
import app.trade.CreateTradeRequest;
import core.framework.web.exception.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

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
        service = new TradeService(1);
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

        service.create(request);
        assertThat(service.trades).hasSize(1);
    }

    @Test
    void trade() {
        String userId = "123456";
        String currencyFrom = "EUR";
        String currencyTo = "GBP";
        BigDecimal amountSell = BigDecimal.valueOf(1000);
        BigDecimal amountBuy = BigDecimal.valueOf(747.1);
        BigDecimal rate = BigDecimal.valueOf(0.7471);
        String timePlaced = "24-JAN-18 10:27:44";
        String originatingCountry = "FR";

        var request = new CreateTradeRequest();
        request.userId = userId;
        request.currencyFrom = currencyFrom;
        request.currencyTo = currencyTo;
        request.amountSell = amountSell;
        request.amountBuy = amountBuy;
        request.rate = rate;
        request.timePlaced = timePlaced;
        request.originatingCountry = originatingCountry;

        Trade trade = service.trade(request);

        assertThat(trade.userId).isEqualTo(userId);
        assertThat(trade.currencyFrom).isEqualTo(currencyFrom);
        assertThat(trade.currencyTo).isEqualTo(currencyTo);
        assertThat(trade.amountSell).isEqualTo(amountSell);
        assertThat(trade.amountBuy).isEqualTo(amountBuy);
        assertThat(trade.timePlaced).isEqualTo(timePlaced);
        assertThat(trade.originatingCountry).isEqualTo(originatingCountry);
    }
}
