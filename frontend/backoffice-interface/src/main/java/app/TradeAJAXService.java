package app;

import app.trade.GetTradeAJAXResponse;
import core.framework.api.web.service.GET;
import core.framework.api.web.service.Path;

/**
 * @author ericchung
 */
public interface TradeAJAXService {
    @GET
    @Path("/ajax/trade")
    GetTradeAJAXResponse get();
}
