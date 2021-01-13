package app.trade;

import core.framework.api.json.Property;
import core.framework.api.validate.NotNull;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author ericchung
 */
public class GetTradeAJAXResponse {
    @NotNull
    @Property(name = "trades")
    public List<Trade> trades;

    public static class Trade {
        @NotNull
        @Property(name = "id")
        public String id;

        @NotNull
        @Property(name = "userId")
        public String userId;

        @NotNull
        @Property(name = "currencyFrom")
        public String currencyFrom;

        @NotNull
        @Property(name = "currencyTo")
        public String currencyTo;

        @NotNull
        @Property(name = "amountSell")
        public BigDecimal amountSell;

        @NotNull
        @Property(name = "amountBuy")
        public BigDecimal amountBuy;

        @NotNull
        @Property(name = "rate")
        public BigDecimal rate;

        @NotNull
        @Property(name = "timePlaced")
        public String timePlaced;

        @NotNull
        @Property(name = "originatingCountry")
        public String originatingCountry;
    }
}
