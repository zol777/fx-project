package app;

import app.trade.CreateTradeRequest;
import core.framework.api.web.service.POST;
import core.framework.api.web.service.Path;

/**
 * @author allison
 */
public interface TradeWebService {
    @POST
    @Path("/trade")
    void create(CreateTradeRequest request);
}
