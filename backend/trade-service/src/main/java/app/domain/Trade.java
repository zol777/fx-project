package app.domain;

import java.math.BigDecimal;

/**
 * @author ericchung
 */
public class Trade {
    public String id;
    public String userId;
    public String currencyFrom;
    public String currencyTo;
    public BigDecimal amountSell;
    public BigDecimal amountBuy;
    public BigDecimal rate;
    public String timePlaced;
    public String originatingCountry;
}
