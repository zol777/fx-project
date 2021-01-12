package app.trade;

import core.framework.api.json.Property;
import core.framework.api.validate.NotNull;

import java.util.List;

/**
 * @author ericchung
 */
public class GetTradeResponse {
    @NotNull
    @Property(name = "trades")
    public List<TradeView> trades;
}
