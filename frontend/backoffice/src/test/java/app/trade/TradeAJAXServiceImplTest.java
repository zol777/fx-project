package app.trade;

import app.TradeWebService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static core.framework.test.Assertions.assertBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.atIndex;
import static org.mockito.Mockito.when;

/**
 * @author ericchung
 */
@ExtendWith(MockitoExtension.class)
class TradeAJAXServiceImplTest {
    private TradeAJAXServiceImpl serviceImpl;
    @Mock
    TradeWebService tradeWebService;

    @BeforeEach
    void createService() {
        serviceImpl = new TradeAJAXServiceImpl();
        serviceImpl.tradeWebService = tradeWebService;
    }

    @Test
    void responseTradeNotNull() {
        assertThat(serviceImpl.trade(new TradeView())).isNotNull();
    }

    @Test
    void responseNotNull() {
        var response = new GetTradeResponse();
        response.trades = List.of();
        when(tradeWebService.get()).thenReturn(response);

        assertThat(serviceImpl.get()).isNotNull();
    }

    @Test
    void validTrade() {
        TradeView trade = trade();

        var response = new GetTradeResponse();
        response.trades = List.of(trade);
        when(tradeWebService.get()).thenReturn(response);

        GetTradeAJAXResponse ajaxResponse = serviceImpl.get();
        assertThat(ajaxResponse.trades).hasSize(1)
                                       .satisfies(argument -> {
                                           assertBean(argument).isValid();
                                           assertEquals(trade, argument);
                                       }, atIndex(0));
    }

    private void assertEquals(TradeView expected, GetTradeAJAXResponse.Trade actual) {
        assertThat(actual.id).isEqualTo(expected.id);
        assertThat(actual.userId).isEqualTo(expected.userId);
        assertThat(actual.currencyFrom).isEqualTo(expected.currencyFrom);
        assertThat(actual.currencyTo).isEqualTo(expected.currencyTo);
        assertThat(actual.amountSell).isEqualTo(expected.amountSell);
        assertThat(actual.amountBuy).isEqualTo(expected.amountBuy);
        assertThat(actual.rate).isEqualTo(expected.rate);
        assertThat(actual.timePlaced).isEqualTo(expected.timePlaced);
        assertThat(actual.originatingCountry).isEqualTo(expected.originatingCountry);
    }

    @Test
    void countryFlagImageURL() {
        String url = serviceImpl.countryFlagImageURL("FR");
        assertThat(url).isEqualTo("https://www.countryflags.io/fr/flat/32.png");
    }

    private TradeView trade() {
        var trade = new TradeView();
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
