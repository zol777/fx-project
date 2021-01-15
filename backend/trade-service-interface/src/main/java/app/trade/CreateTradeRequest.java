package app.trade;

import core.framework.api.json.Property;
import core.framework.api.validate.Min;
import core.framework.api.validate.NotBlank;
import core.framework.api.validate.NotNull;

import java.math.BigDecimal;

/**
 * @author ericchung
 */
public class CreateTradeRequest {
    @NotNull
    @NotBlank
    @Property(name = "userId")
    public String userId;

    @NotNull
    @NotBlank
    @Property(name = "currencyFrom")
    public String currencyFrom;

    @NotNull
    @NotBlank
    @Property(name = "currencyTo")
    public String currencyTo;

    @NotNull
    @Min(0.01)
    @Property(name = "amountSell")
    public BigDecimal amountSell;

    @NotNull
    @Min(0.01)
    @Property(name = "amountBuy")
    public BigDecimal amountBuy;

    @NotNull
    @Min(0)
    @Property(name = "rate")
    public BigDecimal rate;

    @NotNull
    @NotBlank
    @Property(name = "timePlaced")
    public String timePlaced;

    @NotNull
    @NotBlank
    @Property(name = "originatingCountry")
    public String originatingCountry;
}

