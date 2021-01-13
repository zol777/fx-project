package app;

import app.trade.CreateTradeRequest;
import app.trade.GetTradeResponse;
import core.framework.api.web.service.GET;
import core.framework.api.web.service.POST;
import core.framework.api.web.service.Path;

/**
 * @author ericchung
 */
public interface TradeWebService {
    @POST
    @Path("/trade")
    void create(CreateTradeRequest request);

    @GET
    @Path("/trade")
    GetTradeResponse get();
}
