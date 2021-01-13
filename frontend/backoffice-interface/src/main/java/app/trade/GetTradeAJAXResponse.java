package app.trade;

import core.framework.api.json.Property;
import core.framework.api.validate.NotNull;

import java.util.List;

/**
 * @author ericchung
 */
public class GetTradeAJAXResponse {
    @NotNull
    @Property(name = "trades")
    public List<Trade> trades;

    public static class Trade {

    }
}
